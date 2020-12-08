package com.moon.springbootjwtsecurity.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Moon on 12/7/2020
 */

@RestController
@RequestMapping("/message")
public class MessageController {

    @GetMapping
    public ResponseEntity<String> getMessage(){
        return ResponseEntity.ok("Hello Jwt");
    }
}
