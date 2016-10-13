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
import com.webapp.entity.Messages;
import com.webapp.entity.Users;

/**
 *
 * @author yasin
 */
public class UserMessage {

    public static List<Messages> getMessage(int id) {
        Users user = UserDao.getUserById(id);

        List<Messages> list = MessageHandler.getMessageByUserId(user);
        return list;
    }

    public static List<Messages> getMessageByUserEmail(String email) {

        List<Messages> msg = MessageDao.getAllMessage();
        List<Messages> tmp = new ArrayList<Messages>();
        Users us = UserHandler.getUserByName(email);
        
        for (int i = 0; i < msg.size(); i++) {
            if (msg.get(i).getFkUserTo().getId().equals(us.getId())) { //Ändra dessa
                tmp.add(msg.get(i));
            }
        }
       
        
        return tmp;

    }
    
    public static List<Users> getMessageFrom(String name){
        List<Messages> msg=MessageDao.getAllMessage();
        Users us=UserHandler.getUserByName(name);
        String from;int id; List<Users> tmp=new ArrayList<Users>();
        
        for(int i=0;i<msg.size();i++){
            if(msg.get(i).getFkUserTo().getId()==us.getId()){
                id=msg.get(i).getFkUserFrom().getId();
                tmp.add(UserDao.getUserById(id));
                
                
            }
        }
        return tmp;
    }

    public static void main(String[] args) {
        List<Users> list= getMessageFrom("ykoyuncu@kth.se");
        for(int i=0;i<list.size();i++)
        System.out.println(list.get(i).getEmail());
    }
}
