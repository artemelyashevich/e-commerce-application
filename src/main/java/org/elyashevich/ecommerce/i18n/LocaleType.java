package org.elyashevich.ecommerce.i18n;

import lombok.RequiredArgsConstructor;

import java.util.Locale;
import java.util.ResourceBundle;

@RequiredArgsConstructor
public enum LocaleType {

    EN(ResourceBundle.getBundle("message", new Locale("en", "US"))),
    RU(ResourceBundle.getBundle("message", new Locale("ru", "RU")));

    private final ResourceBundle bundle;

    public String getMessage(String key) {
        return bundle.getString(key);
    }
}
