package org.elyashevich.ecommerceapplication.command.category;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.elyashevich.ecommerceapplication.command.Command;
import org.elyashevich.ecommerceapplication.service.CategoryService;

@RequiredArgsConstructor
public class DeleteCategoryCommand implements Command {

    private final CategoryService categoryService;

    @Override
    public void execute(HttpServletRequest request) {
        var id = request.getParameter("id");
        this.categoryService.delete(Long.parseLong(id));
    }
}
