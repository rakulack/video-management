package com.rakulack.videomanagement.component.impl;

import java.io.IOException;
import java.io.InputStream;

import com.oracle.bmc.ConfigFileReader;
import com.oracle.bmc.Region;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.objectstorage.ObjectStorage;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import com.oracle.bmc.objectstorage.requests.GetNamespaceRequest;
import com.oracle.bmc.objectstorage.requests.GetObjectRequest;
import com.oracle.bmc.objectstorage.responses.GetNamespaceResponse;
import com.rakulack.videomanagement.component.FileFetchComponent;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("OCIFileFetch")
@Component
public class OCIFileFetchComponent implements FileFetchComponent {

    String configurationFilePath = "~/.oci/config";
    String profile = "DEFAULT";
    String regionId = Region.AP_TOKYO_1.getRegionId();
    String bucketName = "bucket-20211217-1537";

    @Override
    public InputStream fetchFile(String fileName) throws IOException {
        final ConfigFileReader.ConfigFile configFile = ConfigFileReader.parseDefault();
        final AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(configFile);
        ObjectStorage client = ObjectStorageClient.builder().build(provider);
        client.setRegion(regionId);
        GetNamespaceResponse namespaceResponse = client.getNamespace(GetNamespaceRequest.builder().build());
        String namespaceName = namespaceResponse.getValue();
        GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucketName(bucketName)
                .namespaceName(namespaceName).objectName(fileName).build();
        return client.getObject(getObjectRequest).getInputStream();
    }

}
