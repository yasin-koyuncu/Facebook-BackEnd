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
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.webapp.entity.Users;

/**
 *
 * @author yasin
 */
public class UserDao implements java.io.Serializable {

    public void addUser(Users user) {
       
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Serverlab1PU3");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(user);
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

    public void deleteUser(Integer userId) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Serverlab1PU3");
        EntityManager em = emf.createEntityManager();
        try {

            Users usr = em.find(Users.class, userId);
            if (usr != null) {
                em.getTransaction().begin();
                em.remove(usr);
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

    public static List<Users> getAllUsers() {
        List<Users> users = new ArrayList<Users>();
        
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

    public static Users getUserById(Integer id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Serverlab1PU3");
        EntityManager em = emf.createEntityManager();
        Users users = new Users();

        
        try {
            users = em.find(Users.class, id);
            

        } catch (EntityNotFoundException e) {

            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
        return users;
    }

    public static List<Users> findByName(String name) {

        List<Users> users = getAllUsers();
        List<Users> tmp = new ArrayList<Users>();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getFirstName().contains(name)) {
                tmp.add(users.get(i));
            }

        }

        return tmp;
    }

    public static boolean checkIfUserExists(String username) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Serverlab1PU3");
        EntityManager em = emf.createEntityManager();
        Users users = new Users();

        
        try {
            Query query = em.createQuery("SELECT COUNT(b.email) FROM Users b WHERE b.email:emailparam");
            query.setParameter("emailparam", username);

            int nr = (Integer) query.getSingleResult();
            System.out.println(nr);
            if (nr > 0) {
                System.out.println(nr);
                return true;
            }

        } catch (Exception e) {
            if (em == null) {
                return false;
            }
        } finally {
            em.close();
            emf.close();
        }
        return false;
    }

    public static Users getUserByEmail(String email) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Serverlab1PU3");
        EntityManager em = emf.createEntityManager();
        Users users = new Users();

        
        try {
            Query query = em.createQuery("SELECT u FROM Users u WHERE u.email = :email");
            query.setParameter("email", email);

            users = (Users) query.getSingleResult();
        } catch (Exception e) {
            if (em == null) {
                return null;
            }
        } finally {
            em.close();
            emf.close();
        }
        return users;
    }

}
