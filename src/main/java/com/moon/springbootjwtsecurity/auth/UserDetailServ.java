package com.moon.springbootjwtsecurity.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Moon on 12/7/2020
 */
@Service
public class UserDetailServ implements org.springframework.security.core.userdetails.UserDetailsService {

    private Map<String, String> users = new HashMap<>();   // We will not use a database. so we make a list for understand the User Authentication

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // we create a bean of this in Config class

    @PostConstruct
    public void init(){
        users.put("moon", passwordEncoder.encode("123"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (users.containsKey(username)) {
            return new User(username, users.get(username), new ArrayList<>());  // This user class is an implementation class of UserDetail interface
        }
        throw new UsernameNotFoundException(username);
    }
}
