/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.handler;

import com.webapp.datalayer.MessageDao;
import com.webapp.datalayer.UserDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.webapp.entity.Messages;
import com.webapp.entity.Users;

/**
 *
 * @author yasin
 */
public class MessageHandler {
    
    public static void sendMessageTo(String Subject,String Content,Users from,Users to){
        
        MessageDao.addMessage(new Messages(Subject,Content,from,to));
    }
    public static List<Messages> getMessageByUserId(Users us){
        List<Messages> list=MessageDao.getAllMessage();
        List<Messages> tmp=new ArrayList<Messages>();
        for (int i=0;i<list.size();i++){
              
            if(list.get(i).getFkUserTo().getId()==us.getId())          
                tmp.add(list.get(i));
              
            
        }
        return tmp;
    }
    
    
    /*public static String getMessageFrom(){
        List<Message> list=MessageDao.getAllMessage();
        return null;
    }*/
    
    /*public static void main(String[] args){
        List <Message> l=getMessageByUserId(UserDao.getUserById(3));
        
        for(int i=0;i<l.size();i++)
            System.out.println(""+l.get(i).getSubject());
    }*/
}
