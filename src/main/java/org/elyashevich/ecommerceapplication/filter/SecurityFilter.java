package org.elyashevich.ecommerceapplication.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.elyashevich.ecommerceapplication.util.RouterUtil.ADMIN_PATHS;
import static org.elyashevich.ecommerceapplication.util.RouterUtil.PUBLIC_PATHS;

@WebFilter(urlPatterns = {"/*"})
public class SecurityFilter implements Filter {

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        var req = (HttpServletRequest) request;
        var res = (HttpServletResponse) response;

        var path = req.getServletPath();

        if (PUBLIC_PATHS.contains(path)) {
            chain.doFilter(request, response);
            return;
        }

        var user = req.getSession().getAttribute("userId");
        var role = req.getSession().getAttribute("role");

        if (ADMIN_PATHS.contains(path)) {
            if (role.equals("ADMIN")) {
                chain.doFilter(request, response);
                return;
            }
            res.sendRedirect(req.getContextPath() + "/products");
            return;
        }

        if (user != null) {
            chain.doFilter(request, response);
            return;
        }
        res.sendRedirect(req.getContextPath() + "/login");
    }
}
