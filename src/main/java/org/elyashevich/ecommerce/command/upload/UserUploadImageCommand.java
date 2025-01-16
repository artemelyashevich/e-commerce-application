package org.elyashevich.ecommerce.command.upload;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerce.command.Command;
import org.elyashevich.ecommerce.command.Router;
import org.elyashevich.ecommerce.command.RouterType;
import org.elyashevich.ecommerce.service.UploadService;
import org.elyashevich.ecommerce.service.impl.UploadServiceImpl;

import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUploadImageCommand implements Command {

    @Getter
    private static final UserUploadImageCommand instance = new UserUploadImageCommand();

    private final UploadService uploadService = UploadServiceImpl.getInstance();

    @Override
    public Router execute(final HttpServletRequest request) throws ServletException, IOException {
        var router = new Router();
        var id = (Long) request.getSession().getAttribute("userId");
        var filePart = request.getPart("file");
        var applicationPath = request.getServletContext().getRealPath("");
        this.uploadService.saveUserImage(id, filePart, applicationPath);
        router.setPath("users");
        router.setType(RouterType.REDIRECT);
        return router;
    }
}
