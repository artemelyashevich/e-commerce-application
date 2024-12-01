package org.elyashevich.ecommerceapplication.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.elyashevich.ecommerceapplication.command.CommandProvider;
import org.elyashevich.ecommerceapplication.command.RouterType;
import org.elyashevich.ecommerceapplication.util.JspProvider;

import java.io.IOException;

@WebServlet(name = "controller", urlPatterns = {"/"})
public class Controller extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException  {
        this.processRequest(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        this.processRequest(request, response);
    }

    private void processRequest(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        var action = request.getParameter("command");
        if (action == null || action.isBlank()) {
            action = request.getServletPath().split("/")[1];
        }
        var command = CommandProvider.defineCommand(action);
        var router = command.execute(request);
        var path = JspProvider.getPath(router.getPath());
        System.out.println(router);
        if (router.getType().equals(RouterType.REDIRECT)) {
            response.sendRedirect(router.getPath());
        } else {
            request.getRequestDispatcher(path).forward(request, response);
        }
    }
}
