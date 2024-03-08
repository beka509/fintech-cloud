package kz.fintech.models.excel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExcelExportIin {
    private Integer clientId;
    private String fullName;
    private String iin;
    private String contractNumber;
}
