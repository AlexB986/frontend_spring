package org.example.frontend_spring.secyrity;

import org.example.backend_spring.model.Role;
import org.example.backend_spring.model.User;
import org.example.backend_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class IntinUserToDb {
    @Autowired
    private UserRepository userRepository;
    private WebSecurityConfig webSecurityConfig;


    public IntinUserToDb(UserRepository userRepository, WebSecurityConfig webSecurityConfig) {
        this.userRepository = userRepository;
        this.webSecurityConfig = webSecurityConfig;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");

        User admin = new User();

        admin.setUsername("admin");
        admin.setPassword(webSecurityConfig.passwordEncoder().encode("admin"));
        admin.addUserToRole(adminRole);

        User user = new User();
        user.setUsername("user");
        user.setPassword(webSecurityConfig.passwordEncoder().encode("user"));
        user.addUserToRole(userRole);

        userRepository.save(admin);
        userRepository.save(user);

    }
}
