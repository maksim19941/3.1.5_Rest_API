package com.example.task315restapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class Main {

    static RestTemplate restTemplate = new RestTemplate();
    static String url = "http://94.198.50.185:7081/api/users";

    public static void main(String[] args) throws JsonProcessingException {

        User user = new User();
        user.setId((long)3);
        user.setName("James");
        user.setLastName("Brown");
        user.setAge((byte) 20);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);

        String cookie = responseEntity.getHeaders().getFirst("Set-Cookie");
        System.out.println("Куки " + cookie);
        headers.add("Cookie", cookie);
        System.out.println();

///////////////////////////////////


        System.out.println(restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(user, headers), String.class).getBody());
        System.out.println();


        user.setId((long)3);
        user.setName("Thomas");
        user.setLastName("Shelby");
        user.setAge((byte)20);

        System.out.println(restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(user, headers), String.class).getBody());
        System.out.println();


        System.out.println(restTemplate.exchange(url + "/3", HttpMethod.DELETE, new HttpEntity<>(headers), String.class).getBody());


    }


}
