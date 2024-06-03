package org.example.dao;

import org.example.model.Agent;
import org.example.model.Property;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PropertyDAO {


    public void saveOrUpdate(Property property){

        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=null;

        try {
            transaction=session.beginTransaction();
            session.saveOrUpdate(property);
            transaction.commit();
        }catch (HibernateException e){

            if(transaction!=null)
                transaction.rollback();

            System.out.println("Hata: "+e);
        }finally {
            session.close();
        }

    }

    public Property getPropertyFindById(Long id){
        Session session= HibernateUtil.getSessionFactory().openSession();

        try {
            return session.get(Property.class,id);
        }catch (HibernateException e){
            System.out.println("Hata: "+e);
            return null;
        }finally {
            session.close();
        }

    }

    public List<Property> getPropertyAllFind(){
        Session session= HibernateUtil.getSessionFactory().openSession();

        try {
            return session.createQuery("FROM Property", Property.class).list();
        }catch (HibernateException e){
            System.out.println("Hata: "+e);
            return null;
        }finally {
            session.close();
        }
    }

    public void deletePropertyFindById(Long id){
        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=null;

        try {
            transaction=session.beginTransaction();
            Property property=session.get(Property.class,id);
            if(property!=null){
                session.delete(property);
            }
            transaction.commit();
        }catch (HibernateException e){

            if(transaction!=null)
                transaction.rollback();

            System.out.println("Hata: "+e);
        }finally {
            session.close();
        }
    };

}
