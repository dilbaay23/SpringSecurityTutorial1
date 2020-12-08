package com.moon.springbootjwtsecurity.api;

import com.moon.springbootjwtsecurity.auth.TokenManager;
import com.moon.springbootjwtsecurity.model.LoginModel;
import org.apache.catalina.authenticator.SpnegoAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Moon on 12/8/2020
 */

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;   //it is Springs class. and we create it in WebSecurityConfig class as Bean

    @Autowired
    private TokenManager tokenManager;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginModel loginModel){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginModel.getUsername(), loginModel.getPassword()));
            return ResponseEntity.ok(tokenManager.generateToken(loginModel.getUsername()));

        }catch(Exception e){
            throw e;
        }

    }

}
