package org.elyashevich.ecommerceapplication.service;

import jakarta.servlet.http.Part;

public interface UploadService {

    void saveUserImage(final Long id, final Part part, final String applicationPath);

    void saveProductImage(final Long id, final Part part, final String applicationPath);
}
