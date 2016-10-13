/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.handler;

import com.webapp.datalayer.MessageDao;
import com.webapp.datalayer.PostDao;
import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.List;
import com.webapp.entity.Messages;
import com.webapp.entity.Posts;
import com.webapp.entity.Users;
import java.util.Date;

/**
 *
 * @author yasin
 */
public class PostsHandler {

    public static void addPost(String Content, String from) {

        PostDao.addPost(new Posts(Content, new Date(), UserHandler.getUserByName(from)));
    }

    public static List<Posts> getPostByUserId(int id) {
        List<Posts> list = PostDao.getAllPosts();
        List<Posts> tmp = new ArrayList<Posts>();
        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getFkUserOwner().getId() == id) {
                tmp.add(list.get(i));
            }

        }
        return tmp;
    }

    public static List<Posts> getPostByUserName(String name) {
        List<Posts> post = PostDao.getAllPosts();
        List<Posts> tmp = new ArrayList<Posts>();

        Users us = UserHandler.getUserByName(name);
        System.out.println(post.size());

        for (int i = 0; i < post.size(); i++) {
            if (post.get(i).getFkUserOwner().getId().equals(us.getId())) {
                
                tmp.add(post.get(i));
               // System.out.println(tmp.get(i).getContent());
            }
        }
        return tmp;
    }

    public static void main(String[] args) {
        List<Posts> post=getPostByUserName("ykoyuncu@kth.se");
        
        //for(int i=0;i<post.size();i++)
            //System.out.println(post.get(i).getContent());
    }
}
