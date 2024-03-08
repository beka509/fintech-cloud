package kz.fintech.helpers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public abstract class ZipUtils {
    public static Map<String, byte[]> unzip(byte[] zippedFile) throws IOException {
        Map<String, byte[]> files= new HashMap<>();
        byte[] buffer = new byte[1024];
        try(ByteArrayInputStream bais = new ByteArrayInputStream(zippedFile)) {
            try(ZipInputStream zis = new ZipInputStream(bais)) {
                ZipEntry zipEntry = zis.getNextEntry();
                while (zipEntry != null) {
                    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            baos.write(buffer, 0, len);
                        }
                        files.put(zipEntry.getName(), baos.toByteArray());
                    }
                    zipEntry = zis.getNextEntry();
                }
                zis.closeEntry();
            }
        }
        return files;
    }

    public static byte[] unzipGzip(byte[] zippedFile) throws IOException {
        byte[] buffer = new byte[1024];
        try (ByteArrayInputStream bais = new ByteArrayInputStream(zippedFile)) {
            GZIPInputStream zis = new GZIPInputStream(bais);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len;
            while ((len = zis.read(buffer)) > 0) {
                baos.write(buffer, 0, len);
            }
            zis.close();
            return baos.toByteArray();
        }
    }
}
