package org.elyashevich.ecommerceapplication.service.impl;

import jakarta.servlet.http.Part;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.service.ProductService;
import org.elyashevich.ecommerceapplication.service.UploadService;
import org.elyashevich.ecommerceapplication.service.UserService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UploadServiceImpl implements UploadService {

    @Getter
    private static final UploadServiceImpl instance = new UploadServiceImpl();

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public void saveUserImage(final Long id, final Part part, final String applicationPath) {
        var filePath = this.saveImage(part, applicationPath);
        this.userService.setImage(id, filePath);
    }

    @Override
    public String saveProductImage(final Long id, final Part part, final String applicationPath) {
        return this.saveImage(part, applicationPath);
    }

    private String saveImage(final Part filePart, final String applicationPath) {
        var fileName = filePart.getSubmittedFileName();
        var uploadPath = applicationPath + File.separator + "resources" + File.separator + "images";
        var uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        try (var fileContent = filePart.getInputStream()) {
            Files.copy(fileContent, Paths.get(uploadPath, fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("");
        }
        return uploadPath + File.separator + fileName;
    }
}
