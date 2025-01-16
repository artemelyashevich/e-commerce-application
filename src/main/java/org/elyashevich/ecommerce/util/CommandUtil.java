package org.elyashevich.ecommerce.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CommandUtil {

    public static final String COMMAND_PARAM = "command";

    public static final String INVALID_ACTION = "Action is invalid";

    public static String defineAction(String action, String path) {
        if (action == null || action.isBlank()) {
            action = path.split("/")[1].replace("-", "_");
        }
        return path;
    }
}