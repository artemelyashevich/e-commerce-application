package org.elyashevich.ecommerceapplication.util;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class RouterUtil {

    public static final List<String> PUBLIC_PATHS = List.of("/login", "/register", "/index.jsp");

    public static final List<String> ADMIN_PATHS = List.of("/create-product", "/categories", "/orders", "/users");
}
