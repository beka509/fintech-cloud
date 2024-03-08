package kz.fintech.models.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Document implements Serializable {
    private static final long serialVersionUID = -6110113388768348647L;
    private Integer id;
    private String fileId;
    private String documentNameSuffix;
    private String documentName;
    private String templateCode;
    private String templateArg;
    private DocumentType documentType;
    private Boolean isPhoto;
    private Boolean required;
    private Boolean existsInDossier;
    private Boolean isOriginal;
    @Builder.Default
    private Boolean islocked = false;
    private int pageCount;
    private Date uploadTime;
    private String documentNumber;
    private Date documentDate;
    private Date expirationDate;
    private String md5;
    private String signId;
    private String signedFileId;
}
