package com.rakulack.videomanagement.component.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.oracle.bmc.ConfigFileReader;
import com.oracle.bmc.Region;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.objectstorage.ObjectStorage;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import com.oracle.bmc.objectstorage.model.CommitMultipartUploadDetails;
import com.oracle.bmc.objectstorage.model.CommitMultipartUploadPartDetails;
import com.oracle.bmc.objectstorage.model.CreateMultipartUploadDetails;
import com.oracle.bmc.objectstorage.requests.CommitMultipartUploadRequest;
import com.oracle.bmc.objectstorage.requests.CreateMultipartUploadRequest;
import com.oracle.bmc.objectstorage.requests.GetNamespaceRequest;
import com.oracle.bmc.objectstorage.requests.UploadPartRequest;
import com.oracle.bmc.objectstorage.responses.CreateMultipartUploadResponse;
import com.oracle.bmc.objectstorage.responses.GetNamespaceResponse;
import com.oracle.bmc.objectstorage.responses.UploadPartResponse;
import com.rakulack.videomanagement.component.FileUploadComponent;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@ConfigurationProperties("ocifileupload")
@Component
public class OciFileUploadComponentImpl implements FileUploadComponent {

        String configurationFilePath = "~/.oci/config";
        String profile = "DEFAULT";
        String regionId = Region.AP_TOKYO_1.getRegionId();
        String bucketName = "bucket-20211217-1537";

        @Override
        public String uploadFile(MultipartFile file) throws IOException {

                final ConfigFileReader.ConfigFile configFile = ConfigFileReader.parseDefault();
                final AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(configFile);
                ObjectStorage client = ObjectStorageClient.builder().build(provider);
                client.setRegion(regionId);
                GetNamespaceResponse namespaceResponse = client.getNamespace(GetNamespaceRequest.builder().build());
                String namespaceName = namespaceResponse.getValue();
                CreateMultipartUploadDetails createMultipartUploadDetails = CreateMultipartUploadDetails.builder()
                                .object(file.getOriginalFilename()).contentType(file.getContentType()).build();
                CreateMultipartUploadRequest createMultipartUploadRequest = CreateMultipartUploadRequest.builder()
                                .bucketName(bucketName).namespaceName(namespaceName)
                                .createMultipartUploadDetails(createMultipartUploadDetails).build();
                CreateMultipartUploadResponse createMultipartUploadResponse = client
                                .createMultipartUpload(createMultipartUploadRequest);
                UploadPartRequest uploadPartRequest = UploadPartRequest.builder()
                                .namespaceName(namespaceName)
                                .bucketName(bucketName)
                                .objectName(file.getOriginalFilename())
                                .uploadPartBody(file.getInputStream())
                                .uploadId(createMultipartUploadResponse.getMultipartUpload().getUploadId())
                                .uploadPartNum(1)
                                .build();
                UploadPartResponse uploadPartResponse = client.uploadPart(uploadPartRequest);
                List<CommitMultipartUploadPartDetails> commitMultipartUploadPartDetailsList = new ArrayList<>();
                commitMultipartUploadPartDetailsList
                                .add(CommitMultipartUploadPartDetails.builder().etag(uploadPartResponse.getETag())
                                                .partNum(1).build());
                CommitMultipartUploadDetails commitMultipartUploadDetails = CommitMultipartUploadDetails.builder()
                                .partsToCommit(commitMultipartUploadPartDetailsList).build();
                CommitMultipartUploadRequest commitMultipartUploadRequest = CommitMultipartUploadRequest.builder()
                                .namespaceName(namespaceName)
                                .bucketName(bucketName)
                                .objectName(file.getOriginalFilename())
                                .uploadId(createMultipartUploadResponse.getMultipartUpload().getUploadId())
                                .commitMultipartUploadDetails(commitMultipartUploadDetails)
                                .build();
                client.commitMultipartUpload(commitMultipartUploadRequest);
                return file.getOriginalFilename();
        }

}
