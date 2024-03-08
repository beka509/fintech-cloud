package kz.fintech.dbservice.services;

import kz.fintech.models.excel.ExcelExportIin;
import kz.fintech.models.excel.ExcelExportRequest;
import kz.fintech.models.excel.ExcelExportRequestRepo;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FileService {
    List<ExcelExportRequestRepo> getListClientsInformation();
    List<ExcelExportIin> getListClients();
    List<ExcelExportRequestRepo> getListBySegment(Integer segmentId);
    void saveFile(List<ExcelExportRequest> excelExports);
    void saveContract(List<ExcelExportRequest> excelExports);
}
