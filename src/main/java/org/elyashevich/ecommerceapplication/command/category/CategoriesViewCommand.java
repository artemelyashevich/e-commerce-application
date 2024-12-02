package org.elyashevich.ecommerceapplication.command.category;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.command.Command;
import org.elyashevich.ecommerceapplication.command.Router;
import org.elyashevich.ecommerceapplication.command.RouterType;
import org.elyashevich.ecommerceapplication.mapper.CategoryMapper;
import org.elyashevich.ecommerceapplication.mapper.impl.CategoryMapperImpl;
import org.elyashevich.ecommerceapplication.service.CategoryService;
import org.elyashevich.ecommerceapplication.service.impl.CategoryServiceImpl;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoriesViewCommand implements Command {

    private final CategoryService categoryService = CategoryServiceImpl.getInstance();
    private final CategoryMapper categoryMapper = CategoryMapperImpl.getInstance();

    @Getter
    private static final CategoriesViewCommand instance = new CategoriesViewCommand();

    @Override
    public Router execute(final HttpServletRequest request) {
        var router = new Router();
        var categories = this.categoryService.findAll();
        request.setAttribute("categories", this.categoryMapper.toDto(categories));
        router.setType(RouterType.FORWARD);
        router.setPath("categories");
        return router;
    }
}
