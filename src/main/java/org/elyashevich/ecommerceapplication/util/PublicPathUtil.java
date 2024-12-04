package org.elyashevich.ecommerceapplication.util;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class PublicPathUtil {

    public static final List<String> PUBLIC_PATHS = List.of("/login", "/register", "/index.jsp");
}
