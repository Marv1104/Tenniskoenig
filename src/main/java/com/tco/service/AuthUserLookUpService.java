package com.tco.service;

import org.springframework.stereotype.Service;

import com.tco.model.User;

@Service
public class AuthUserLookUpService {

    User findUser(String username) {
        User found = null;
        switch (username) {
            case "admin":
                return new User("admin", "admin");
        }
        return found;
    }
}
