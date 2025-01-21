package org.vt.model;


import java.util.Objects;

public class FileDto {

    private final String fileName;
    private final String filePath;
    private final long fileSize;
    private final Storage storage;

    private FileDto(String fileName, String filePath, long fileSize, Storage storage) {
        this.fileName = Objects.requireNonNull(fileName, "fileName cannot be null");
        this.filePath = Objects.requireNonNull(filePath, "filePath cannot be null");
        this.fileSize = fileSize;
        this.storage = Objects.requireNonNull(storage, "fileLocation cannot be null");
    }

    public static FileDto of(String fileName, String filePath, long fileSize, Storage storage) {
        return new FileDto(fileName, filePath, fileSize, storage);
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

    public Storage getFileLocation() {
        return storage;
    }

    @Override
    public String toString() {
        return "FileDto{" + "fileName='" + fileName + '\'' + ", filePath='" + filePath + '\'' + ", fileSize=" + fileSize + ", fileLocation=" + storage + '}';
    }
}
