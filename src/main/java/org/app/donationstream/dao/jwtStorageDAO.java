package org.app.donationstream.dao;

import org.app.donationstream.entity.jwtStorage;
import org.app.donationstream.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.exception.GenericJDBCException;

import java.util.List;

public class jwtStorageDAO {
    public static List<jwtStorage> getJwtTokens() {
        Session session = HibernateUtil.sessionFactory.openSession();
        try {
            List<jwtStorage> jwtStorage = session.createQuery("from jwtStorage", jwtStorage.class).getResultList();
            session.close();
            return jwtStorage;
        } catch (GenericJDBCException e) {
            jwtStorage jwtStorage = new jwtStorage(0, "", "");
            session.persist(jwtStorage);
            return null;
        }
    }

    public static void setJwtTokens(List<jwtStorage> jwtTokens) {
        HibernateUtil.sessionFactory.inTransaction(session -> {
            session.persist(jwtTokens);
        });
    }
}
