package org.example.dao;

import org.example.model.Agent;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AgentDAO {


    public void saveOrUpdate(Agent agent){

        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=null;

        try {
            transaction=session.beginTransaction();
            session.saveOrUpdate(agent);
            transaction.commit();
        }catch (HibernateException e){

            if(transaction!=null)
            transaction.rollback();

            System.out.println("Hata: "+e);
        }finally {
            session.close();
        }

    }

    public Agent getAgentFindById(Long id){
        Session session= HibernateUtil.getSessionFactory().openSession();

        try {
            //session.createQuery("FROM Agent",Agent.class) Queryli kodaları daha karmaşık isteklerde kullanırız
            return session.get(Agent.class,id);
        }catch (HibernateException e){
            System.out.println("Hata: "+e);
            return null;
        }finally {
            session.close();
        }

    }

    public List<Agent> getAgentAllFind(){
        Session session= HibernateUtil.getSessionFactory().openSession();

        try {
            return session.createQuery("FROM Agent",Agent.class).list();
        }catch (HibernateException e){
            System.out.println("Hata: "+e);
            return null;
        }finally {
            session.close();
        }
    }

    public void deleteAgentFindById(Long id){
        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=null;

        try {
            transaction=session.beginTransaction();
            Agent agent=session.get(Agent.class,id);
            if(agent!=null){
                session.delete(agent);
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
