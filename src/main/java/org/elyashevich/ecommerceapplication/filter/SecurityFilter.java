package org.elyashevich.ecommerceapplication.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.elyashevich.ecommerceapplication.util.JspProvider;
import org.elyashevich.ecommerceapplication.util.PublicPathUtil;

import java.io.IOException;

import static org.elyashevich.ecommerceapplication.util.PublicPathUtil.PUBLIC_PATHS;

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

        var user = req.getSession().getAttribute("user");
        if (user != null) {
            chain.doFilter(request, response);
            return;
        }
        res.sendRedirect(req.getContextPath() + "/login");
    }
}