package org.example.util;


import org.example.model.Agent;
import org.example.model.Buyer;
import org.example.model.Property;
import org.example.model.Seller;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                //MYSQL 8 için
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/real_estate_db?useSSL=false");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "4321");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");

                /*
                //POSTGRESQL
                Properties settings = new Properties();

                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/companydb");
                settings.put(Environment.USER, "postgres");
                settings.put(Environment.PASS, "4321");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
                settings.put(Environment.SHOW_SQL, "true"); */

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);
// cnf.xml dosyasındaki gibi mapp işlemi yerine ilgilenilecek dosyalar burada tanıtılyr.
                configuration.addAnnotatedClass(Agent.class);
                configuration.addAnnotatedClass(Buyer.class);
                configuration.addAnnotatedClass(Seller.class);
                configuration.addAnnotatedClass(Property.class);


                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}