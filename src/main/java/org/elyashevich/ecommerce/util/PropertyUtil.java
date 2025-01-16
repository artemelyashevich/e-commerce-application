package org.elyashevich.ecommerce.util;

import lombok.experimental.UtilityClass;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

@UtilityClass
public class PropertyUtil {

    private static final String ERROR_TEMPLATE = "Unable to load '%s.properties' file";

    public static String loadProperty(final String filename, final String key) {
        try {
            var resource = ResourceBundle.getBundle(filename);
            return resource.getString(key);
        } catch (MissingResourceException e) {
            throw new IllegalStateException(ERROR_TEMPLATE.formatted(filename), e);
        }
    }
}
