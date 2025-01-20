package org.elyashevich.ecommerce.service.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerce.i18n.LocaleType;
import org.elyashevich.ecommerce.service.LocaleService;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocaleServiceImpl implements LocaleService {

    @Getter
    private static final LocaleService instance = new LocaleServiceImpl();

    @Override
    public LocaleType defineLocale(String language) {
        return LocaleType.valueOf(language);
    }
}
