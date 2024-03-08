package kz.fintech.models.file;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class File {
    private String fileName;
    private int fileSize;
    private byte[] fileContents;
    private FileType fileType;

    public String toBase64() {
        if (fileContents == null || fileContents.length == 0) return "";
        return java.util.Base64.getEncoder().encodeToString(fileContents);
    }
}
