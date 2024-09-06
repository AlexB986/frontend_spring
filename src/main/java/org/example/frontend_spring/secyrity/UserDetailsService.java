package org.example.frontend_spring.secyrity;



import org.example.frontend_spring.ClientRepository.ClientRepository;
import org.example.frontend_spring.model.Role;
import org.example.frontend_spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    public UserDetailsService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    //    Поиск user в БД
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = clientRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
//        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),user.getAuthorities());
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getAuthorities(user.getRoles()));
    }
    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList());
    }
}
