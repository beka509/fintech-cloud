package kz.fintech.services;

import kz.fintech.models.excel.ExcelExportRequestRepo;
import kz.fintech.models.excel.ExcelResponse;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface FileService {
    String sendExcelFtp();

    String importExcelToDb(MultipartFile file);

    String sendExcelBySegmentToFtp(Integer segmentId);
}
