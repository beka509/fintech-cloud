package kz.fintech.commons.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = ServiceNames.FILE_SERVICE, url = ServiceUrls.FILE_SERVICE)
public interface FileServiceClient {
    @PostMapping(value = "/file/excel/importToDb", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String importExcelToDb(@RequestPart("file") MultipartFile file);

    @PostMapping("/file/excel/sendFtp")
    String sendExcelFtp();

    @PostMapping("/file/excel/sendFtp/by-segment/{segmentId}")
    String sendExcelFtpBySegment(@PathVariable("segmentId") Integer segmentId);
}
