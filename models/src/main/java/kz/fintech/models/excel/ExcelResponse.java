package kz.fintech.models.excel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExcelResponse {
    private List<ExcelExportRequest> excelExportRequestsNew;
    private List<ExcelExportRequest> excelExportRequestsExisting;
}
