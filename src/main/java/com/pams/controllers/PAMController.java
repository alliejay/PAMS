package com.pams.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pams.entities.User;
import com.pams.services.ItemRepository;
import com.pams.services.UserRepository;
import com.pams.utils.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by MattBrown on 12/8/15.
 */
@RestController
public class PAMController {

    @Autowired
    UserRepository users;

    @Autowired
    ItemRepository items;

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
            String password
    )throws Exception {
        session.setAttribute("username", username);
        User user = users.findOneByUsername(username);
        if (user == null){
            user = new User();
            user.username = username;
            user.password = PasswordHash.createHash(password);
            user.accessLevel = User.AccessLevel.ADMIN;
            users.save(user);
        }
        else if (!PasswordHash.validatePassword(password, user.password)){
            throw new Exception ("Wrong password!");
        }
    }
    @RequestMapping("/create-company")
    public User companyUser(
            HttpSession session,
            String companyUsername,
            String companyPassword,
            String companyName,
            String email
    )throws Exception{
        String username = (String) session.getAttribute("username");
        if (username == null){
            throw new Exception ("You cant create a user!");
        }
        User user = users.findOneByUsername(username);
        if (user == null){
            user = new User();
            user.username = companyUsername;
            user.password = companyPassword;
            user.accessLevel = User.AccessLevel.COMPANY_USER;
            user.companyName = companyName;
            user.email = email;
            users.save(user);
        }
        return user;
    }
    @RequestMapping("/create-retailer")
    public User retailerUser(
            HttpSession session,
            String retailerUsername,
            String retailerPassword,
            String email,
            String companyName,
            String address,
            String city,
            String state,
            int zip
    )throws Exception{
        String username = (String) session.getAttribute("username");
        if (username == null){
            throw new Exception ("You cant create a user!");
        }
        User user = users.findOneByUsername(username);
        if (user == null){
            user = new User();
            user.username = retailerUsername;
            user.password = retailerPassword;
            user.accessLevel = User.AccessLevel.RETAILER_USER;
            user.email = email;
            user.companyName = companyName;
            user.address = address;
            user.city = city;
            user.state = state;
            user.zip = zip;
            users.save(user);
        }
        return user;

    }
    @RequestMapping("create-joe")
    public User joeUser(
            HttpSession session,
            String joeUsername,
            String joePassword
    )throws Exception{
        String username = (String) session.getAttribute("username");
        if (username == null){
            throw new Exception ("You cant create a user!");
        }
        User user = users.findOneByUsername(username);
        if (user == null){
            user = new User();
            user.username = joeUsername;
            user.password = joePassword;
            user.accessLevel = User.AccessLevel.JOE_USER;
            users.save(user);
        }
        return user;
    }
}
