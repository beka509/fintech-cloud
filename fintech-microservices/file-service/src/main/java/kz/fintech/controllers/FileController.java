package kz.fintech.controllers;

import kz.fintech.models.excel.ExcelResponse;
import kz.fintech.services.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/excel/importToDb")
    public String importExcelToDb(@RequestParam("file") MultipartFile file) {
        return fileService.importExcelToDb(file);
    }

    @PostMapping("/excel/sendFtp")
    public String sendExcelFtp() {
        return fileService.sendExcelFtp();
    }

    @PostMapping("/excel/sendFtp/by-segment/{segmentId}")
    public String sendExcelFtpBySegment(@PathVariable("segmentId") Integer segmentId) {
        return fileService.sendExcelBySegmentToFtp(segmentId);
    }
}
