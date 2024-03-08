package kz.fintech.models.refs.location;

import kz.fintech.models.LocalizedString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostObject {
    private String id;
    private ObjectName name;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ObjectName{
        private LocalizedString name;
        private LocalizedString shortName;
    }
}
