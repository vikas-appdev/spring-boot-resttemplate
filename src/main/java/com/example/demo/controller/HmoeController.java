package com.example.demo.controller;

import com.example.demo.io.User;
import com.example.demo.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HmoeController {

    private final ServiceLayer serviceLayer;

    @Autowired
    public HmoeController(ServiceLayer serviceLayer) {
        this.serviceLayer = serviceLayer;
    }

    @GetMapping("/")
    public ResponseEntity<User> getData(){
        return new ResponseEntity<>(serviceLayer.consumeAPI(), HttpStatus.OK);
    }

    @PostMapping(path = "/accept", consumes = "application/x-www-form-urlencoded")
    public User hello(User user){
        System.out.println(user.getUserId());
        System.out.println(user.getTitle());
        return user;
    }

    @GetMapping(path = "/hello")
    public ResponseEntity<User> hello1(){
        return serviceLayer.consumeAPI2();
    }
}
