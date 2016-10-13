/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


/**
 *
 * @author yasin
 */
@Entity 
@Table(name="messages")
public class Message implements Serializable{
    
    private Integer id;
    private String Subject;
    private String Content;
    private Users from;
    private Users to;
    
    public Message(){
        
    }
    public Message( String Subject, String Content,Users from,Users to) {
       
        this.Subject = Subject;
        this.Content = Content;
        this.from = from;
        this.to = to;
    }
    @Id @GeneratedValue
    @Column(name="Id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
     @Column(name="Subject")
    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }
     @Column(name="Content")
    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }
   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "Fk_User_From")
   public Users getFrom() {
        return from;
    }

    public void setFrom(Users from) {
        this.from = from;
    }
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="Fk_User_To")
    public Users getTo() {
        return to;
    }

    public void setTo(Users to) {
        this.to = to;
    }

   
    
    public static void main(String[] args){
        Users usFrom= new Users();
        //Users usTo= new Users("Pontus","Mats","pm@hotmail.com","password");
        Messages message=new Messages();
        Posts post=new Posts();
      
        
        
        /*Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            trns = session.beginTransaction();
           
      
            session.save(usFrom);
            session.save(usTo);
             Message m=new Message("hallå","Svara da",usFrom,usTo);
             
            session.save(m);
            session.getTransaction().commit();

        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.flush();
            session.close();
        }*/
    }
    
    
}

/**
 * package db;
// Generated 2015-nov-09 22:27:19 by Hibernate Tools 4.3.1




public class Messages  implements java.io.Serializable {


     private Integer id;
     private Users usersByFkUserFrom;
     private Users usersByFkUserTo;
     private String subject;
     private String content;

    public Messages() {
    }

    public Messages(Users usersByFkUserFrom, Users usersByFkUserTo, String subject, String content) {
       this.usersByFkUserFrom = usersByFkUserFrom;
       this.usersByFkUserTo = usersByFkUserTo;
       this.subject = subject;
       this.content = content;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Users getUsersByFkUserFrom() {
        return this.usersByFkUserFrom;
    }
    
    public void setUsersByFkUserFrom(Users usersByFkUserFrom) {
        this.usersByFkUserFrom = usersByFkUserFrom;
    }
    public Users getUsersByFkUserTo() {
        return this.usersByFkUserTo;
    }
    
    public void setUsersByFkUserTo(Users usersByFkUserTo) {
        this.usersByFkUserTo = usersByFkUserTo;
    }
    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }




}



 */