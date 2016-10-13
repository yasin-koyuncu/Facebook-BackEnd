/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.service;

import com.webapp.handler.UserHandler;
import java.util.Collection;
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
import com.webapp.entity.Users;

/**
 *
 * @author Muki Avdic
 */
@Path("users")
public class UsersFacadeREST {

  
    /**
     * Get user by id
     * @param id
     * @return user 
     */
    @GET
    @Path("/id/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Users find(@PathParam("id") Integer id) { 
        return com.webapp.handler.UserHandler.getUserById(id);
    }
    /**
     * Get all users
     * @return  users
     */
    @GET
    @Path("/id/all/")
    @Produces({MediaType.APPLICATION_JSON})  //Get all users
    public List<Users> getAllUsers() {
        return com.webapp.handler.UserHandler.getUsers();
    }
    /**
     * Get user by name(find by name)
     * @param name
     * @return user
     */
    
    @GET
    @Path("/name/{name}")
    @Produces({MediaType.APPLICATION_JSON})   
    public Users findByName(@PathParam("name") String name) {
        return com.webapp.handler.UserHandler.getUserByName(name);
    }
    
    
    /**
     * Register user
     * @param firstName
     * @param lastName
     * @param email
     * @param password 
     * @param secretKey 
     */
    @POST
    @Path("/register")
    public void registerUser(       ///Funkar nu
            @FormParam("FirstName") String firstName,
            @FormParam("LastName") String lastName,
            @FormParam("Email") String email,
            @FormParam("Password") String password,
            @FormParam("secretKey") String secretKey)
            {
        
        com.webapp.handler.UserHandler.saveUser(new Users(firstName, lastName, email, password,secretKey));
    }
    
    
    /**
     * login method
     * @param email
     * @param password
     * @param otpKey
     * @return true or false
     */
    @GET
    @Path("/login/{Email}/{Password}/{otpKey}")
    @Produces({MediaType.APPLICATION_JSON})     //Funkar nu
    public String loginUser(@PathParam("Email") String email,
            @PathParam("Password") String password, @PathParam("otpKey") String otpKey){
         if(com.webapp.handler.UserHandler.login(email, password,otpKey))
            return "true";
        else
           return "false";
    }
    
    /**
     * Search user by name
     * @param search
     * @return collection of users
     */
    @GET
    @Path("/search/{search}")
    @Produces({MediaType.APPLICATION_JSON})   //Funkar n
    public Collection<Users> searchByName(@PathParam("search") String search) {
        return com.webapp.handler.UserHandler.getUserNamesByName(search);
    }
    

   

    

  
    
}
