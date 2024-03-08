package kz.fintech.models.file;

import kz.fintech.models.LocalizedString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentType implements Serializable {
    private static final long serialVersionUID = 7121714644516198418L;

    public static final String STATEMENT = "STATEMENT";
    public static final String CUSTOMER_PHOTO = "CUSTOMER_PHOTO";

    private String id;
    private String parentId;
    private String colvirCodeKz;
    private String colvirCodeRu;
    private LocalizedString name;
    private String documentTypeTemplateCode;
}
