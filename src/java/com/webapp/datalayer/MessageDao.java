/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.datalayer;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.webapp.entity.Messages;
import com.webapp.entity.Users;


/**
 *
 * @author yasin
 */
public class MessageDao {

    public static void addMessage(Messages m) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Serverlab1PU3");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(m);
            em.getTransaction().commit();

        } catch (RuntimeException e) {
            if (em != null) {
                
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();

        }
    }

    public static void deleteMessage(Integer id) {

        EntityManagerFactory emf=Persistence.createEntityManagerFactory("Serverlab1PU3");
        EntityManager em=emf.createEntityManager();
        try {
         
            Messages msg=em.find(Messages.class, id);
            if(msg!=null){
                em.getTransaction().begin();
                em.remove(msg);
                em.getTransaction().commit();
            }

        } catch (RuntimeException e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }

    public static List<Messages> getAllMessage() {
        List<Messages> msg = new ArrayList<Messages>();
        //Transaction trns = null;
        //Session session = HibernateUtil.getSessionFactory().openSession();
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("Serverlab1PU3");
        EntityManager em=emf.createEntityManager();
        try {
            javax.persistence.Query query=em.createQuery("select a from Messages a");
            msg=query.getResultList();
        } catch (RuntimeException e) {
            
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
        return msg;

    }

    public static void main(String[] args) {
        List <Messages> l=getAllMessage();

       for(int i=0;i<l.size();i++)
           System.out.println(""+l.get(i).getContent()+" "+l.get(i).getSubject());
        //addMessage(new Messages("hallpj", "ska vi käka", UserDao.getUserById(1), UserDao.getUserById(2)));
        //deleteMessage(20);
    }
}
