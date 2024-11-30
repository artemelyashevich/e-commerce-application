package org.elyashevich.ecommerceapplication.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.elyashevich.ecommerceapplication.command.CategoryCommandFactory;
import org.elyashevich.ecommerceapplication.dao.impl.CategoryDaoImpl;
import org.elyashevich.ecommerceapplication.mapper.CategoryMapper;
import org.elyashevich.ecommerceapplication.mapper.impl.CategoryMapperImpl;
import org.elyashevich.ecommerceapplication.service.CategoryService;
import org.elyashevich.ecommerceapplication.service.impl.CategoryServiceImpl;
import org.elyashevich.ecommerceapplication.util.JspProvider;
import org.elyashevich.ecommerceapplication.util.QueryParameterUtil;

import java.io.IOException;

@WebServlet(name = "categories", value = "/categories")
public class CategoryServlet extends HttpServlet {

    private CategoryService categoryService;
    private CategoryMapper categoryMapper;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        var id = QueryParameterUtil.getParameters(request).isEmpty() ? "" : QueryParameterUtil.getParameters(request).get("id")[0];
        if (id != null && !id.isBlank()) {
            var category = this.categoryService.findById(Long.parseLong(id));
            request.setAttribute("category", category);
            request.getRequestDispatcher(JspProvider.getPath("category")).forward(request, response);
            return;
        }
        var categories = this.categoryService.findAll();
        request.setAttribute("categories", this.categoryMapper.toDto(categories));
        request.getRequestDispatcher(JspProvider.getPath("categories")).forward(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        var method = request.getParameter("_method");
        var commandFactory = new CategoryCommandFactory(categoryService, categoryMapper);
        var command = commandFactory.getCommand(method);
        command.execute(request);
        this.doGet(request, response);
    }

    @Override
    public void init() throws ServletException {
        this.categoryService = new CategoryServiceImpl(CategoryDaoImpl.getInstance());
        this.categoryMapper = CategoryMapperImpl.getInstance();
    }
}
