package kz.fintech.apiservice.controllers;

import kz.fintech.commons.feignclients.FileServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api")
@RestController
@Slf4j
//@Api(value = "api", description = "API для file-service")
public class FileController {

    private final FileServiceClient fileServiceClient;

    @Autowired
    public FileController(FileServiceClient fileServiceClient) {
        this.fileServiceClient = fileServiceClient;
    }

   // @ApiOperation("Import данные из excel файла в БД")
    @PostMapping(value = "/excel/importToDb")
    public String importExcelToDb(@RequestParam("file") MultipartFile file) {
        return fileServiceClient.importExcelToDb(file);
    }

  //  @ApiOperation("Экспорт excel файла")
    @PostMapping(value = "/excel/sendFtp")
    public String sendExcelFtp() {
        return fileServiceClient.sendExcelFtp();
    }

  //  @ApiOperation("Экспорт excel файла по id Segment")
    @PostMapping(value = "/excel/sendExcelFtpBySegment/{segmentId}")
    public String sendExcelFtpBySegment(@PathVariable("segmentId") Integer segmentId) {
        return fileServiceClient.sendExcelFtpBySegment(segmentId);
    }
}
