package org.elyashevich.ecommerce.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JspProviderUtil {

    public static String getPath(final String jspName) {
        return "/pages/%s.jsp".formatted(jspName);
    }
}
