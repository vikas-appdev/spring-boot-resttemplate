package com.example.demo.service;

import com.example.demo.io.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class ServiceLayer {
    private final RestTemplate restTemplate;

    @Autowired
    public ServiceLayer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public User consumeAPI(){
        return restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos/1", User.class);

    }

    public ResponseEntity<User> consumeAPI2(){
        //return restTemplate.getForObject("localhost:8080/accept", User.class);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("userId", "1");

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("userId", "1");
        map.add("id", "1");
        map.add("title", "1");
        map.add("completed", "true");
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<User> response =
                restTemplate.exchange("http://localhost:8080/accept",
                        HttpMethod.POST,
                        entity,
                        User.class);

        System.out.println(response.getBody().getTitle());

        return response;


    }

}
