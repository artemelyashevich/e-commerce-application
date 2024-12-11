package org.elyashevich.ecommerceapplication.service;

import jakarta.servlet.http.Part;

public interface UploadService {

    void saveUserImage(final Long id, final Part part, final String applicationPath);

    String saveProductImage(final Long id, final Part part, final String applicationPath);
}
