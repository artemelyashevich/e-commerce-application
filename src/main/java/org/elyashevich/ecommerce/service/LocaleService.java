package org.elyashevich.ecommerce.service;

import org.elyashevich.ecommerce.i18n.LocaleType;


public interface LocaleService {

    LocaleType defineLocale(String language);
}
