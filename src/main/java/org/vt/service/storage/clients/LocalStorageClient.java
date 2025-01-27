package org.vt.service.storage.clients;

import org.vt.dto.StorageType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class LocalStorageClient implements StorageClient {

    @Override
    public StorageType storageType() {
        return StorageType.LOCAL;
    }

    @Override
    public InputStream receiveInputData(String fileName, String filePath) {
        String path = filePath.endsWith("/") ? filePath : filePath + "/";
        String fullPath = path + fileName;
        try {
            return new FileInputStream(fullPath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void uploadReport(String report) {
        System.out.println(report);
    }
}
