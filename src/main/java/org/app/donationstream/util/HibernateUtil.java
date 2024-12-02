package org.app.donationstream.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.app.donationstream.RunApplication;

import java.io.File;
import java.util.List;

public class HibernateUtil {
    public static SessionFactory sessionFactory;

    public static void setUp() {
        // A SessionFactory is set up once for an application!
        File path = new File("");
        String absolutePath = path.getAbsolutePath().replace("\\", "/");
        RunApplication.appPath = absolutePath;
        String dbPath = String.format("jdbc:sqlite:%s/Databases.db", absolutePath);
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySetting("hibernate.connection.url", dbPath)
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            Alerts.createAndShowWarning(e.getMessage());
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    public static Integer getId(List<Integer> listId) {
        int i;
        for (i = 0; i < Integer.MAX_VALUE; i++) {
            try {
                if (!listId.contains(i)) {
                    break;
                }
            } catch (IndexOutOfBoundsException e) {
                return --i;
            }
        }
        return i;
    }
}
