package kz.fintech.commons.components;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class MultipartFileResource extends ByteArrayResource {

    private final String filename;

    public MultipartFileResource(MultipartFile multipartFile) throws IOException {
        super(multipartFile.getBytes());
        this.filename = multipartFile.getOriginalFilename();
    }

    @Override
    public String getFilename() {
        return this.filename;
    }
}