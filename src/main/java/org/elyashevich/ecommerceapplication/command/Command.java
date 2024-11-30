package org.elyashevich.ecommerceapplication.command;

import jakarta.servlet.http.HttpServletRequest;

public interface Command {

    void execute(final HttpServletRequest request);
}
