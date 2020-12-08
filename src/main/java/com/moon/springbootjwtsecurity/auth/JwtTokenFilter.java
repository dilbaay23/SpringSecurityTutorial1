package com.moon.springbootjwtsecurity.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Moon on 12/7/2020
 */

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenManager tokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        /**
        "Bearer 123hab2355"
         */

        final String authHeader = request.getHeader("Authorization");

        String token=null;
        String username=null;

        if (authHeader !=null && authHeader.contains("Bearer")){
            token = authHeader.substring(7);

            try {
             username=tokenManager.getUsernameToken(token);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
        if(username != null && token !=null
                && SecurityContextHolder.getContext().getAuthentication()==null){   //SecurityContextHolder.getContext().getAuthentication()==null is to control if the user has logged in the system before.

            if(tokenManager.tokenValidate(token)){
                UsernamePasswordAuthenticationToken userPasswordToken=
                        new UsernamePasswordAuthenticationToken(username,null,new ArrayList<>());
                userPasswordToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(userPasswordToken);
            }
        }
        filterChain.doFilter(request,response);

    }
}
