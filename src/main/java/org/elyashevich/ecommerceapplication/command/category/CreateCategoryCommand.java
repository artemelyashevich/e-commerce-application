package org.elyashevich.ecommerceapplication.command.category;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.command.Command;
import org.elyashevich.ecommerceapplication.command.Router;
import org.elyashevich.ecommerceapplication.command.RouterType;
import org.elyashevich.ecommerceapplication.dao.impl.CategoryDaoImpl;
import org.elyashevich.ecommerceapplication.dto.CategoryDto;
import org.elyashevich.ecommerceapplication.mapper.CategoryMapper;
import org.elyashevich.ecommerceapplication.mapper.impl.CategoryMapperImpl;
import org.elyashevich.ecommerceapplication.service.CategoryService;
import org.elyashevich.ecommerceapplication.service.impl.CategoryServiceImpl;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateCategoryCommand implements Command {

    @Getter
    private static final CreateCategoryCommand instance = new CreateCategoryCommand();

    private final CategoryService categoryService = new CategoryServiceImpl(CategoryDaoImpl.getInstance());
    private final CategoryMapper categoryMapper = CategoryMapperImpl.getInstance();

    @Override
    public Router execute(final HttpServletRequest request) {
        var router = new Router();
        var categoryDto = CategoryDto.builder()
                .name(request.getParameter("name"))
                .build();
        this.categoryService.create(this.categoryMapper.toEntity(categoryDto));
        router.setPath("categories");
        router.setType(RouterType.REDIRECT);
        return router;
    }
}
