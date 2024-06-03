package org.example.dao;

import org.example.model.Agent;
import org.example.model.Buyer;
import org.example.model.Seller;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SellerDAO {


    public void saveOrUpdate(Seller seller){

        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=null;

        try {
            transaction=session.beginTransaction();
            session.saveOrUpdate(seller);
            transaction.commit();
        }catch (HibernateException e){

            if(transaction!=null)
                transaction.rollback();

            System.out.println("Hata: "+e);
        }finally {
            session.close();
        }

    }

    public Seller getSellerFindById(Long id){
        Session session= HibernateUtil.getSessionFactory().openSession();

        try {
             return session.get(Seller.class,id);
        }catch (HibernateException e){
            System.out.println("Hata: "+e);
            return null;
        }finally {
            session.close();
        }

    }

    public List<Seller> getSellerAllFind(){
        Session session= HibernateUtil.getSessionFactory().openSession();

        try {
            return session.createQuery("FROM Seller",Seller.class).list();
        }catch (HibernateException e){
            System.out.println("Hata: "+e);
            return null;
        }finally {
            session.close();
        }
    }

    public void deleteSellerFindById(Long id){
        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=null;

        try {
            transaction=session.beginTransaction();
            Seller seller=session.get(Seller.class,id);
            if(seller!=null){
                session.delete(seller);
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
