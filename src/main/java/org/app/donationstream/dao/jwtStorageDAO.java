package org.app.donationstream.dao;

import org.app.donationstream.entity.jwtStorage;
import org.app.donationstream.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.exception.GenericJDBCException;

import java.util.List;

public class jwtStorageDAO {
    public List<jwtStorage> getJwtTokens() {
        try (Session session = HibernateUtil.sessionFactory.openSession()) {
            return session.createQuery("from jwtStorage", jwtStorage.class).getResultList();
        } catch (GenericJDBCException e) {
            //create table
            return null;
        }
    }

    public void setJwtTokens(List<jwtStorage> jwtTokens) {
        HibernateUtil.sessionFactory.inTransaction(session -> {
            session.persist(jwtTokens);
        });
    }
}
