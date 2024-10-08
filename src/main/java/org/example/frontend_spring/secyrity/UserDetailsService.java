package org.example.frontend_spring.secyrity;

import org.example.frontend_spring.pojo.AvtorizationUserDTO;
import org.example.frontend_spring.pojo.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private RestTemplate restTemplate;


    //    Поиск user в БД
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AvtorizationUserDTO user =restTemplate.getForObject("http://localhost:8080/admin/ver/" + username, AvtorizationUserDTO.class);
        if(user == null){
            throw new UsernameNotFoundException(username);

        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getAuthorities(user.getRoles()));
    }
    private Collection<? extends GrantedAuthority> getAuthorities(Collection<RoleDTO> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList());
    }
}
