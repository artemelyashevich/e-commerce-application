package org.elyashevich.ecommerceapplication.command.category;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.command.Command;
import org.elyashevich.ecommerceapplication.command.Router;
import org.elyashevich.ecommerceapplication.command.RouterType;
import org.elyashevich.ecommerceapplication.dao.impl.CategoryDaoImpl;
import org.elyashevich.ecommerceapplication.service.CategoryService;
import org.elyashevich.ecommerceapplication.service.impl.CategoryServiceImpl;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteCategoryCommand implements Command {

    @Getter
    private static final DeleteCategoryCommand instance = new DeleteCategoryCommand();

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
