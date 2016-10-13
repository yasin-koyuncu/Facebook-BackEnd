/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.datalayer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;

import com.webapp.entity.Messages;
import com.webapp.entity.Posts;


/**
 *
 * @author yasin
 */
public class PostDao {
    
     public static void addPost(Posts p){
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Serverlab1PU3");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(p);
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
       public static Posts getPostsById(Integer id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Serverlab1PU3");
        EntityManager em = emf.createEntityManager();
        Posts posts = new Posts();
      
        try {
            posts = em.find(Posts.class, id);
            

        } catch (EntityNotFoundException e) {
            if(em ==null)
                return null;
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
        return posts;
    }
   
    
      public static List<Posts> getAllPosts(){
          List<Posts> posts = new ArrayList<Posts>();
       
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("Serverlab1PU3");
        EntityManager em=emf.createEntityManager();
        try {
            javax.persistence.Query query=em.createQuery("select a from Posts a");
            posts=query.getResultList();
        } catch (RuntimeException e) {
            
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
        return posts;
      
    }
     
    
}
