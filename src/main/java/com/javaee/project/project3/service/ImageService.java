package com.javaee.project.project3.service;

import com.javaee.project.project3.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.persistence.EntityExistsException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
public class ImageService {
    @Value("${file.upload.profileImage}")
    private String UPLOAD_DIR;

    public String uploadImageGetPath(MultipartFile file) {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = String.valueOf(uuid);
        Path filepath = Paths.get(UPLOAD_DIR + randomUUIDString + Objects.requireNonNull(file.getOriginalFilename())
                .substring(file.getOriginalFilename()
                        .lastIndexOf(".")));
        try (OutputStream os = Files.newOutputStream(filepath)) {
            os.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(filepath.getFileName());
    }

    public byte[] getImageById(Product product) {
        String fullPath = UPLOAD_DIR + product.getBigImagePath();
        String format = product.getBigImagePath().substring(product.getBigImagePath().lastIndexOf(".") + 1);
        System.out.println(format);
        File serverFile=new File(fullPath);
        try {
            return Files.readAllBytes(serverFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new EntityExistsException("ERROR");
    }

}
