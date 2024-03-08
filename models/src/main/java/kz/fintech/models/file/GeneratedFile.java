package kz.fintech.models.file;

import lombok.Data;

@Data
public class GeneratedFile {
    private FileType fileType;
    private byte[] contents;
    private boolean success;
    private String errorMessage;
}
