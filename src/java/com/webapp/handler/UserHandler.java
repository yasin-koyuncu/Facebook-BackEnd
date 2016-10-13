/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.handler;

import com.webapp.security.HashBCrypt;

import com.webapp.datalayer.UserDao;
import java.util.ArrayList;
import java.util.List;
import com.webapp.entity.Users;
import com.webapp.GoogleAuthenticator.Totp;
import org.jboss.aerogear.security.otp.api.Base32;


/**
 *
 * @author yasin
 */
public class UserHandler {

    /**
     * Register the user in db.It also returns the key. 
     *
     * @param us
     * @return
     */
    public static String saveUser(Users us) {
        UserDao db = new UserDao();
        us.setKey(getUniqueSecretKey());

        if (checkIfUserExist(us)) {
            db.addUser(us);
            return "User with " + us.getEmail() + " saved succesfully";
        }

        return "User exist in database";

    }

    /**
     * Return a unique key for each user. This key is for googleauthenticator
     * app
     *
     * @return
     */
    public static String getUniqueSecretKey() {
        String secretKey = Base32.random();

        List<Users> list = UserDao.getAllUsers();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKey() != null) {
                if (list.get(i).getKey().equals(secretKey)) {
                    secretKey = Base32.random();
                    i = 0;

                }
            }
        }

        return secretKey;
    }

    /**
     * This method check if the user already exists in db. And return true in
     * that case.
     *
     * @param us
     * @return
     */
    public static boolean checkIfUserExist(Users us) {
        UserDao db = new UserDao();
        boolean find = false;
        List<Users> users = db.getAllUsers();
        try {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getEmail().equals(us.getEmail())) {
                    find = true;
                }
            }
        } catch (Exception e) {
            if (us == null) {
                return false;
            }
        }

        return find == true;

    }

    /**
     * This one is the login process. It use hash password (Bcrypt) and otpKey.
     * OtpKey is the value where you can see in the google authenticator app.
     * This value changes after 30s.
     *
     * @param username
     * @param password
     * @param otpKey
     * @return
     */
    public static boolean login(String username, String password, String otpKey) {

        String hash = HashBCrypt.hashPass(password);
        Users user = UserDao.getUserByEmail(username);

        if (user != null) {
            Totp totp = new Totp(user.getKey());
            if (user.getEmail().equals(username) && HashBCrypt.matchedPass(password, user.getPassword()) && totp.verify(otpKey)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return all users
     *
     * @return
     */
    public static List<Users> getUsers() {
        UserDao db = new UserDao();
        return db.getAllUsers();
    }

    /**
     * Get a user with the email-address.
     *
     * @param from
     * @return
     */
    public static Users getUserByName(String from) {
        List<Users> users = UserDao.getAllUsers();
        Users tmp;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(from)) {
                tmp = users.get(i);
                return tmp;
            }
        }
        return null;
    }

    /**
     * Get the user with id
     *
     * @param id
     * @return
     */
    public static Users getUserById(int id) {
        return UserDao.getUserById(id);
    }

    /**
     * Search name based on firstname.
     *
     * @param name
     * @return
     */
    public static List<Users> searchUsers(String name) {
        List<Users> tmp = UserDao.getAllUsers();
        List<Users> users = new ArrayList<Users>();

        for (int i = 0; i < tmp.size(); i++) {
            if (tmp.get(i).getFirstName().contains(name)) {
                users.add(tmp.get(i));
            }
        }
        return users;
    }

    /**
     * Return list of users you searched.
     *
     * @param name
     * @return
     */
    public static List<Users> getUserNamesByName(String name) {
        ArrayList<Users> names = new ArrayList<Users>();

        if (name == null || name.isEmpty() || name.equals("")) {
            name = " ";
        }
        List<Users> users = UserDao.findByName(name);
        for (Users user : users) {
            names.add(new Users(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword()));
        }

        return names;
    }

}
