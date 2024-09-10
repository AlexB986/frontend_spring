package org.example.frontend_spring.service;

import org.example.frontend_spring.pojo.FullUserDTO;
import org.example.frontend_spring.pojo.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.*;

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
        String url = URL + "/read";
        ResponseEntity<List<UserDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserDTO>>() {}
        );
        System.out.println(response.getBody());
        return response.getBody();

    }

    @Override
    public void deleteUser(Long id) {
        String url = URL + "/delete/" + id;
        restTemplate.delete(url);
    }
}