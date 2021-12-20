package com.rakulack.videomanagement.component.impl;

import java.io.IOException;

import com.oracle.bmc.ConfigFileReader;
import com.oracle.bmc.Region;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.objectstorage.ObjectStorage;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import com.oracle.bmc.objectstorage.requests.DeleteObjectRequest;
import com.oracle.bmc.objectstorage.requests.GetNamespaceRequest;
import com.oracle.bmc.objectstorage.responses.GetNamespaceResponse;
import com.rakulack.videomanagement.component.FileDeleteComponent;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("ocifiledelete")
@Component
public class OciFileDeleteComponentImpl implements FileDeleteComponent {

    String configurationFilePath = "~/.oci/config";
    String profile = "DEFAULT";
    String regionId = Region.AP_TOKYO_1.getRegionId();
    String bucketName = "bucket-20211217-1537";

    @Override
    public void deleteFile(String fileName) throws IOException {
        final ConfigFileReader.ConfigFile configFile = ConfigFileReader.parseDefault();
        final AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(configFile);
        ObjectStorage client = ObjectStorageClient.builder().build(provider);
        client.setRegion(regionId);
        GetNamespaceResponse namespaceResponse = client.getNamespace(GetNamespaceRequest.builder().build());
        String namespaceName = namespaceResponse.getValue();
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder().bucketName(bucketName)
                .namespaceName(namespaceName).objectName(fileName).build();
        client.deleteObject(deleteObjectRequest);
    }

}
