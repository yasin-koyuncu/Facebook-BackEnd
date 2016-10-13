/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.handler;

import java.util.List;
import com.webapp.entity.Friends;
import com.webapp.datalayer.FriendsDao;

/**
 *
 * @author yasin93
 */
public class FriendsHandler {
    
    public static List<Friends> getFriendsById(int id){
        return FriendsDao.getFriendsByUserId(id);
    }
    
    
}
