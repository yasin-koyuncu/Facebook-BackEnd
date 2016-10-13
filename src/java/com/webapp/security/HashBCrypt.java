/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.security;

import java.security.NoSuchAlgorithmException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author yasin93
 */
public class HashBCrypt {
    
      
     public static String hashPass(String originalPass){
         return BCrypt.hashpw(originalPass, getSalt());
     }
     
     public static boolean matchedPass(String originalPass,String hashPass){    
         boolean matched=BCrypt.checkpw(originalPass, hashPass);       
         return matched;
     }
     private static String getSalt(){
         return BCrypt.gensalt(12);
     }
}
