package com.pams.controllers;

import com.pams.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by MattBrown on 12/8/15.
 */
public class PAMController {

    @Autowired
    UserRepository users;
}
