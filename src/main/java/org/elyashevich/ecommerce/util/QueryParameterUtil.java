package org.elyashevich.ecommerce.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class QueryParameterUtil {

    public static Map<String, String[]> getParameters(final HttpServletRequest request){
        return Collections.list(request.getParameterNames())
                .stream()
                .collect(Collectors.toMap(parameterName -> parameterName, request::getParameterValues));
    }
}
