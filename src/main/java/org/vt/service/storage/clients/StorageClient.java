package org.vt.service.storage.clients;

import org.vt.dto.StorageType;

import java.io.InputStream;

public interface StorageClient {
    StorageType storageType();

    InputStream receiveInputData(String fileName, String filePath);

    void uploadReport(final String report);
}
