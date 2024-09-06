package org.example.frontend_spring.ClientRepository;
import org.example.frontend_spring.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
