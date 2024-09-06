package org.example.frontend_spring.service;

import org.example.frontend_spring.pojo.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientServiceImpl implements ClientService {
    private final String URL = "http://localhost:8080/client";
    @Autowired
    private RestTemplate restTemplate;

    public ClientServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public void addUser(UserDTO user) {
        String url = URL + "/users";
        Map<String, UserDTO> request = new HashMap<>();
        request.put("user", user);
        // возвращает тело ответа
        restTemplate.postForObject(url, request, UserDTO.class);
    }

    @Override
    public void updateUser(UserDTO user) {
        String url = URL + "/users";
        Map<String, UserDTO> request = new HashMap<>();
        request.put("user", user);
        restTemplate.put(url, request);
    }

    @Override
    public UserDTO findUserById(Long id) {
        String url = URL + "/users/" + id;
        return restTemplate.getForObject(url, UserDTO.class);
    }

    @Override
    public List<UserDTO> findAllUsers() {
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
