package kz.fintech.helpers;

import kz.fintech.models.file.File;
import lombok.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URLDecoder;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ImageUtils {

    public static BufferedImage resize(Image originalImage,
                                       int scaledWidth, int scaledHeight,
                                       boolean preserveAlpha) {
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
        Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
            g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }

    @SneakyThrows
    public static byte[] getBytes(BufferedImage image) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(image, "jpg", baos);
            baos.flush();
            return baos.toByteArray();
        }
    }

    @SneakyThrows
    public static BufferedImage toImage(byte[] bytes) {
        try (ByteArrayInputStream in = new ByteArrayInputStream(bytes)) {
            return ImageIO.read(in);
        }
    }

    public static BufferedImage collateImages(java.util.List<File> photoFiles, int imageWidth, int imageMargin, int imagesInRow) {

        List<BufferedImage> photos = photoFiles.stream()
                .filter(file -> file.getFileContents() != null && file.getFileContents().length > 0)
                .map(file -> ImageUtils.toImage(file.getFileContents()))
                .map(image -> ImageUtils.resize(image, imageWidth, image.getHeight() * imageWidth / image.getWidth(), true))
                .collect(Collectors.toList());

        if (!photos.isEmpty()) {
            val totalWidth = photos.size() >= imagesInRow
                    ? imagesInRow * imageWidth + (imagesInRow + 1) * imageMargin
                    : photos.size() * imageWidth + (photos.size() + 1) * imageMargin;
            var totalHeight = 0;
            for (int i = 0; i < photos.size(); i += imagesInRow) {
                var maxHeight = 0;
                for (int j = 0; j < imagesInRow && i + j < photos.size(); j++) {
                    val height = photos.get(i + j).getHeight();
                    if (maxHeight < height) {
                        maxHeight = height;
                    }
                }
                totalHeight += maxHeight + imageMargin;
            }
            totalHeight += imageMargin;
            val concatImage = new BufferedImage(totalWidth, totalHeight, BufferedImage.TYPE_INT_RGB);
            @Cleanup(value = "dispose") val g2d = concatImage.createGraphics();

            g2d.setPaint(new Color(255, 255, 255));
            g2d.fillRect(0, 0, totalWidth, totalHeight);

            var offset = imageMargin;
            for (int i = 0; i < photos.size(); i += imagesInRow) {

                var maxHeight = 0;
                for (int j = 0; j < imagesInRow && i + j < photos.size(); j++) {
                    val photo = photos.get(i + j);
                    val height = photo.getHeight();
                    if (maxHeight < height) {
                        maxHeight = height;
                    }
                    g2d.drawImage(photo, j * (imageWidth + imageMargin) + imageMargin, offset, null);
                }
                offset += maxHeight + imageMargin;
            }
            return concatImage;

        }
        return null;
    }

    public static DataImage parseDataImage(String string) {
        if (string != null && string.startsWith("data:")) {
            val data = string.substring(5).split(",");
            if (data.length > 1) {
                var contentType = "";
                var encoding = "url";
                if (data[0] != null) {
                    if (data[0].contains(";")) {
                        String[] split = data[0].split(";");
                        contentType = split[0];
                        encoding = split[1];
                    } else {
                        contentType = data[0];
                    }
                }

                val fileContents = "base64".equalsIgnoreCase(encoding)
                        ? Base64.getDecoder().decode(data[1])
                        : decodeUrlEncodedImageData(data[1]);
                val fileName = UUID.randomUUID().toString() + guessImageExtensionByMime(contentType);
                return new DataImage(fileName, contentType, fileContents);
            }
        }
        return null;
    }

    @SneakyThrows
    private static byte[] decodeUrlEncodedImageData(String data) {
        byte[] result = new byte[data.length() / 3];
        for (int i = 0, j = 0; i < data.length(); i += 3, j++) {
            String s = URLDecoder.decode(data.substring(i, i + 3), "ASCII");
            result[j] = Byte.parseByte(s);
        }
        return result;
    }

    private static String guessImageExtensionByMime(String mimeType) {
        if (mimeType == null) return "";
        if ("image/gif".equalsIgnoreCase(mimeType)) return ".gif";
        if ("image/jpeg".equalsIgnoreCase(mimeType)) return ".jpg";
        if ("image/png".equalsIgnoreCase(mimeType)) return ".png";
        return "";
    }

    @Getter
    @RequiredArgsConstructor
    public static class DataImage {
        private final String fileName;
        private final String contentType;
        private final byte[] contents;
    }
}
