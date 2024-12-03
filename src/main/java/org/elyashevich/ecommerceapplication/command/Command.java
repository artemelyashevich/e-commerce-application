package org.elyashevich.ecommerceapplication.command;

import jakarta.servlet.http.HttpServletRequest;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface Command {

    Router execute(final HttpServletRequest request);
}
