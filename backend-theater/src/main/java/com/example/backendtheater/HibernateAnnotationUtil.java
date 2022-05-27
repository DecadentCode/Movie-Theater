package com.example.backendtheater;

import com.example.backendtheater.product.PaidTicket;
import com.example.backendtheater.product.Ticket;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateAnnotationUtil {
    private static SessionFactory sessionFactory;

    static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }

    private static SessionFactory buildSessionFactory() {

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate-annotation.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Ticket.class)
                //.addAnnotatedClass(PaidTicket.class)
                /*.addAnnotatedClass(Address.class)
                .addAnnotatedClass(Amount.class)
                .addAnnotatedClass(Capture.class)
                .addAnnotatedClass(Link.class)
                .addAnnotatedClass(Name.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(Payee.class)
                .addAnnotatedClass(Payer.class)
                .addAnnotatedClass(Payments.class)
                .addAnnotatedClass(PurchaseUnit.class)
                .addAnnotatedClass(SellerProtection.class)
                .addAnnotatedClass(Shipping.class)
                .addAnnotatedClass(HashMapConverter.class)*/
                // other domain classes
                .getMetadataBuilder()
                .build();

        return metadata.getSessionFactoryBuilder().build();
    }
}
