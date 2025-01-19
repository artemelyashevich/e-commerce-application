package org.elyashevich.ecommerce.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.elyashevich.ecommerce.command.CommandProvider;
import org.elyashevich.ecommerce.command.RouterType;
import org.elyashevich.ecommerce.exception.DaoException;
import org.elyashevich.ecommerce.exception.PasswordMismatchException;
import org.elyashevich.ecommerce.exception.ResourceNotFoundException;
import org.elyashevich.ecommerce.util.JspProviderUtil;

import java.io.IOException;

@WebServlet(name = "controller", urlPatterns = {"/"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
public class Controller extends HttpServlet {
    private static final String DEFAULT_ACTION = "home";
    private static final String RESOURCES_ACTION = "resources";

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        String action = resolveAction(request);

        if (RESOURCES_ACTION.equals(action)) {
            forwardRequest(request, response);
        } else {
            try {
                executeCommand(action, request, response);
            }
            catch (RuntimeException e) {
                request.setAttribute("error", e.getMessage());
            }
            catch (Exception e) {
                request.setAttribute("error", e.getMessage());
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            }
        }
    }

    private String resolveAction(HttpServletRequest request) {
        String action = request.getParameter("command");
        if (action == null || action.isBlank()) {
            action = request.getServletPath().split("/")[1].replace("-", "_");
            if (action.isBlank()) {
                action = DEFAULT_ACTION;
            }
        }
        return action;
    }

    private void forwardRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(request.getServletPath()).forward(request, response);
    }

    private void executeCommand(String action, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            var command = CommandProvider.defineCommand(action);
            var router = command.execute(request);
            String path = JspProviderUtil.getPath(router.getPath());
            if (router.getType().equals(RouterType.REDIRECT)) {
                response.sendRedirect(router.getPath());
            } else {
                request.getRequestDispatcher(path).forward(request, response);
            }
        } catch (Exception e) {
            handleError(request, response, e);
        }
    }

    private void handleError(HttpServletRequest request, HttpServletResponse response, Exception e)
            throws ServletException, IOException {
        e.printStackTrace();
        request.setAttribute("error", "An error occurred: " + e.getMessage());
        request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
    }
}

