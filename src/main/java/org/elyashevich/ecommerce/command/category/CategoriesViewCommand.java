package org.elyashevich.ecommerce.command.category;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerce.command.Command;
import org.elyashevich.ecommerce.command.Router;
import org.elyashevich.ecommerce.command.RouterType;
import org.elyashevich.ecommerce.mapper.CategoryMapper;
import org.elyashevich.ecommerce.mapper.impl.CategoryMapperImpl;
import org.elyashevich.ecommerce.service.CategoryService;
import org.elyashevich.ecommerce.service.impl.CategoryServiceImpl;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoriesViewCommand implements Command {

    @Getter
    private static final CategoriesViewCommand instance = new CategoriesViewCommand();

    private final CategoryService categoryService = CategoryServiceImpl.getInstance();
    private final CategoryMapper categoryMapper = CategoryMapperImpl.getInstance();

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
