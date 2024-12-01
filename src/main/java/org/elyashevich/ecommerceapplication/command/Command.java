package org.elyashevich.ecommerceapplication.command;

import jakarta.servlet.http.HttpServletRequest;

public interface Command {

    Router execute(final HttpServletRequest request);
}
