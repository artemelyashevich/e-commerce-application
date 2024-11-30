package org.elyashevich.ecommerceapplication.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.elyashevich.ecommerceapplication.dao.impl.ProductDaoImpl;
import org.elyashevich.ecommerceapplication.service.ProductService;
import org.elyashevich.ecommerceapplication.service.impl.ProductServiceImpl;

import java.io.IOException;

@WebServlet(name = "products", value = "/products")
public class ProductServlet extends HttpServlet {

    private ProductService productService;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        var products = this.productService.findAll();
        System.out.println(products);
        request.setAttribute("products", products);
        request.getRequestDispatcher("/pages/products.jsp").forward(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        this.productService = new ProductServiceImpl(ProductDaoImpl.getInstance());
    }
}
