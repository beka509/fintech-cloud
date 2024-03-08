package kz.fintech.models.file;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HashCheckResponse {
    private boolean isError;
    private String content;
}
