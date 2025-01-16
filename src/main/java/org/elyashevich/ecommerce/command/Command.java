package org.elyashevich.ecommerce.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public interface Command {

    Router execute(final HttpServletRequest request) throws ServletException, IOException;
}
