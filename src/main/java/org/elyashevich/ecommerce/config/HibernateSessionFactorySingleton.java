package org.elyashevich.ecommerce.config;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Getter
public final class HibernateSessionFactorySingleton {

    private final SessionFactory sessionFactory;

    private HibernateSessionFactorySingleton() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    private static class SingletonHolder {
        private static final SessionFactory INSTANCE = new HibernateSessionFactorySingleton().getSessionFactory();
    }

    public static SessionFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
