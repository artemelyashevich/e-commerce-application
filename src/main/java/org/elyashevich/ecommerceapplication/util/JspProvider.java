package org.elyashevich.ecommerceapplication.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JspProvider {

    public static String getPath(final String jspName) {
        return "/pages/%s.jsp".formatted(jspName);
    }
}
