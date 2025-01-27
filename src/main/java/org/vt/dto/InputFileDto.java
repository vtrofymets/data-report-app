package org.vt.dto;

import java.util.Objects;

public class InputFileDto {

    private final String fileName;
    private final String filePath;
    private final long fileSize;
    private final StorageType storageType;
    private final ReportType reportType;

    private InputFileDto(String fileName, String filePath, long fileSize, StorageType storageType,
            ReportType reportType) {
        this.fileName = Objects.requireNonNull(fileName, "fileName cannot be null");
        this.filePath = Objects.requireNonNull(filePath, "filePath cannot be null");
        this.fileSize = fileSize;
        this.storageType = Objects.requireNonNull(storageType, "fileLocation cannot be null");
        this.reportType = Objects.requireNonNull(reportType, "reportType cannot be null");
    }

    public static InputFileDto of(String fileName, String filePath, long fileSize, StorageType storageType,
            ReportType reportType) {
        return new InputFileDto(fileName, filePath, fileSize, storageType, reportType);
    }

    public String getFullPath() {
        String path = filePath.endsWith("/") ? filePath : filePath + "/";
        return path + fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public StorageType getStorage() {
        return storageType;
    }

    public ReportType getReportType() {
        return reportType;
    }

    @Override
    public String toString() {
        return "FileDto{" + "fileName='" + fileName + '\'' + ", filePath='" + filePath + '\'' + ", fileSize=" + fileSize + ", storageType=" + storageType + ", reportType=" + reportType + '}';
    }
}
