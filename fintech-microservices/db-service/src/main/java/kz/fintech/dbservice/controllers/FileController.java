package kz.fintech.dbservice.controllers;

import kz.fintech.dbservice.services.FileService;
import kz.fintech.models.excel.ExcelExportIin;
import kz.fintech.models.excel.ExcelExportRequest;
import kz.fintech.models.excel.ExcelExportRequestRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/fintech")
public class FileController {
    public final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/file/excel/saveFile")
    public void saveFile(@RequestBody List<ExcelExportRequest> excelExport) {
        fileService.saveFile(excelExport);
    }

    @PostMapping("/file/excel/saveContract")
    public void saveContract(@RequestBody List<ExcelExportRequest> excelExport) {
        fileService.saveContract(excelExport);
    }

    @GetMapping(value = "/getListClientsInformation")
    public List<ExcelExportRequestRepo> getListClientsInformation() {
        return fileService.getListClientsInformation();
    }

    @GetMapping(value = "/getListClients")
    public List<ExcelExportIin> getListClients() {
        return fileService.getListClients();
    }

    @GetMapping("/getList/by-segment/{segmentId}")
    public List<ExcelExportRequestRepo> findListBySegment(@PathVariable("segmentId") Integer segmentId) {
        return fileService.getListBySegment(segmentId);
    }
}
