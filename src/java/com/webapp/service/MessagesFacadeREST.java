/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.service;

import com.webapp.handler.UserHandler;
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
import com.webapp.entity.Messages;

/**
 *
 * @author yasin93
 */

@Path("messages")
public class MessagesFacadeREST {


    @POST
    @Path("/addmessage")
    public void addMessage(
            @FormParam("Subject") String subject,
            @FormParam("Content") String content,
            @FormParam("UserFrom") String from,
            @FormParam("UserTo") String to) {
        
        com.webapp.handler.MessageHandler.sendMessageTo(subject, content, UserHandler.getUserByName(from), UserHandler.getUserByName(to));
    }
    
    @GET
    @Path("/getmessages/{from}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Messages> getMessages(@PathParam("from") String from){
        return com.webapp.handler.MessageHandler.getMessageByUserId(UserHandler.getUserByName(from));
    }
    
    
    
    
}
