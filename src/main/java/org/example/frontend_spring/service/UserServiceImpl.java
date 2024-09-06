package org.example.frontend_spring.service;

import org.example.frontend_spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private final String URL = "http://localhost:8080/admin";
    @Autowired
    private RestTemplate restTemplate;

    public UserServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public void addUser(User user) {
        String url = URL + "/users";
        Map<String, User> request = new HashMap<>();
        request.put("user", user);
        // возвращает тело ответа
        restTemplate.postForObject(url, request, User.class);
    }

    @Override
    public void updateUser(User user) {
        String url = URL + "/users";
        Map<String, User> request = new HashMap<>();
        request.put("user", user);
        restTemplate.put(url, request);
    }

    @Override
    public User findUserById(Long id) {
        String url = URL + "/users/" + id;
        return restTemplate.getForObject(url, User.class);
    }

    @Override
    public List<User> findAllUsers() {
        String url = URL + "/users";
        return restTemplate.getForObject(url, List.class);
//        List<User> userList = restTemplate.getForObject(url, User.class);
//        return userList;
    }


    @Override
    public void deleteUser(Long id) {
        String url = URL + "/users/"+id;
        restTemplate.delete(url);
    }
}
