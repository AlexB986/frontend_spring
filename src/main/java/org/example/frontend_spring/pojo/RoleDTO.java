package org.example.frontend_spring.pojo;


import java.io.Serializable;

public class RoleDTO implements Serializable {


    public Long id;
    public String role;

    public RoleDTO() {
    }

    public RoleDTO(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
