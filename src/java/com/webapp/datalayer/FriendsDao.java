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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.webapp.entity.Friends;
import com.webapp.entity.Messages;
import com.webapp.entity.Users;
import org.hibernate.Criteria;

/**
 *
 * @author yasin93
 */
public class FriendsDao {
    
    public static void addFriends(Friends m) {

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
     public static List<Friends> getAllFriends() {
        List<Friends> msg = new ArrayList<Friends>();
        //Transaction trns = null;
        //Session session = HibernateUtil.getSessionFactory().openSession();
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("Serverlab1PU3");
        EntityManager em=emf.createEntityManager();
        try {
            javax.persistence.Query query=em.createQuery("select a from Friends a");
            msg=query.getResultList();
        } catch (RuntimeException e) {
            
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
        return msg;

    }
    public static List<Friends> getFriendsByUserId(int id){  ///Gets friends of a user
          List<Friends> msg = new ArrayList<Friends>();
        //Transaction trns = null;
        //Session session = HibernateUtil.getSessionFactory().openSession();
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("Serverlab1PU3");
        EntityManager em=emf.createEntityManager();
        try {
            
            
            
            javax.persistence.Query query=em.createQuery("select a from Friends a where a.userId.id=:id");
            query.setParameter("id", id);
            msg=query.getResultList();
        } catch (RuntimeException e) {
            
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
        return msg;
    }
    
     public static 
             Friends deleteUser(Integer id, Integer friendId) { //id är den inloggade id:n
               List<Friends> msg = new ArrayList<Friends>();
               Friends friends = null;
        //Transaction trns = null;
        //Session session = HibernateUtil.getSessionFactory().openSession();
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("Serverlab1PU3");
        EntityManager em=emf.createEntityManager();
        try {
            
            
            
            javax.persistence.Query query=em.createQuery("select a from Friends a where a.userId.id=:id and a.friendId.id=:friendId");
            query.setParameter("id", id);
            query.setParameter("friendId", friendId);
            friends =(Friends) query.getSingleResult();
            
            //kör sedan en remove
        } catch (RuntimeException e) {
            
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
        return friends;
   
    }

    public static List<Users> getAllUsers() {
        List<Users> users = new ArrayList<Users>();
        //Transaction trns = null;
        //Session session = HibernateUtil.getSessionFactory().openSession();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Serverlab1PU3");
        EntityManager em = emf.createEntityManager();
        try {
            javax.persistence.Query query = em.createQuery("select a from Users a");
            users = query.getResultList();
        } catch (RuntimeException e) {

            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
        return users;
    }

    
    public static void main(String[] args){
        addFriends(new Friends(UserDao.getUserById(1),UserDao.getUserById(2)));
       Friends list=deleteUser(3,1);
        
     
            System.out.println(list.getUserId().getId()+ " "+list.getFriendId().getId()   );
        
 
    }
}
