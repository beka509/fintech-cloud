package kz.fintech.models.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileTemplate {
    private String templateCode;
    private String filePath;
    private String description;
}
