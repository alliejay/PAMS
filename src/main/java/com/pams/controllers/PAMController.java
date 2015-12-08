package com.pams.controllers;

import com.pams.entities.User;
import com.pams.services.UserRepository;
import com.pams.utils.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

/**
 * Created by MattBrown on 12/8/15.
 */
public class PAMController {

    @Autowired
    UserRepository users;

    /*@PostConstruct
    public void loadData(){
        String fileContent = readFile("players.csv");
        String[] lines = fileContent.split("\r");
        if ()

    }*/


    @RequestMapping("/login")
    public void login(
            HttpSession session,
            String username,
            String password,
            int accessLevel,
            String email) throws Exception {
        session.setAttribute("username", username);
        User user = users.findOneByUsername(username);
        if (user == null){
            user = new User();
            user.username = username;
            user.password = password;
            user.accessLevel = accessLevel;
            user.email = email;
            users.save(user);
        }
        else if (!PasswordHash.validatePassword(password, user.password)){
            throw new Exception ("Wrong password!");
        }
    }
}
