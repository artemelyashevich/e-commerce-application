package org.elyashevich.ecommerce.command.category;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerce.command.Command;
import org.elyashevich.ecommerce.command.Router;
import org.elyashevich.ecommerce.command.RouterType;
import org.elyashevich.ecommerce.service.CategoryService;
import org.elyashevich.ecommerce.service.impl.CategoryServiceImpl;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryDeleteCommand implements Command {

    @Getter
    private static final CategoryDeleteCommand instance = new CategoryDeleteCommand();

    private final CategoryService categoryService = CategoryServiceImpl.getInstance();

    @Override
    public Router execute(final HttpServletRequest request) {
        var router = new Router();
        var id = request.getParameter("id");
        this.categoryService.delete(Long.parseLong(id));
        router.setType(RouterType.REDIRECT);
        router.setPath("categories");
        return router;
    }
}
