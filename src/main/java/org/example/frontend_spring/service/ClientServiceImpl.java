package org.example.frontend_spring.service;

import org.example.frontend_spring.pojo.FullUserDTO;
import org.example.frontend_spring.pojo.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientServiceImpl implements ClientService {
    private final String URL = "http://localhost:8080/admin";

    @Autowired
    private RestTemplate restTemplate;


    public ClientServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void addUser(FullUserDTO user) {
        String url = URL + "/add";
        Map<String, FullUserDTO> request = new HashMap<>();
        request.put("user", user);
        // возвращает тело ответа
        restTemplate.postForObject(url, request, FullUserDTO.class);
    }

    @Override
    public void updateUser(FullUserDTO user) {
        String url = URL + "/id";
        Map<String, FullUserDTO> request = new HashMap<>();
        request.put("user", user);
        restTemplate.put(url, request);
    }

    @Override
    public UserDTO findUserById(Long id) {
        String url = URL + "/read/" + id;
        return restTemplate.getForObject(url, UserDTO.class);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        String url = URL + "/read";
//        return restTemplate.getForObject(url, List.class);
       UserDTO userList = restTemplate.getForObject(url, UserDTO.class);
       users.add(userList);
        return users;
    }


    @Override
    public void deleteUser(Long id) {
        String url = URL + "/delete/"+id;
        restTemplate.delete(url);
    }
}
