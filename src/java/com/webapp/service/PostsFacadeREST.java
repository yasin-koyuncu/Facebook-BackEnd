/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.webapp.entity.Posts;

/**
 *
 * @author yasin93
 */
@Path("posts")
public class PostsFacadeREST {

    @POST
    @Path("/addpost")
    public void addPost(@FormParam("Content") String content, @FormParam("From") String from) {
        com.webapp.handler.PostsHandler.addPost(content, from);
    }

    @GET
    @Path("/getposts/{from}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Posts> getPosts(@PathParam("from") String from) {
        return com.webapp.handler.PostsHandler.getPostByUserName(from);
    }

}
