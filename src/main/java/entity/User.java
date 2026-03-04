package main.java.entity;

import java.util.Objects;

public class User {
    private String name;
    private String role;
    private String email;
    public User(String name, String role, String email){
        this.name = name;
        this.role = role;
        this.email = email;
    }
    //getters for variables
    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj==null || getClass()!=obj.getClass()) return false;
        User u=(User)obj;
        return name.equals(u.name) && role.equals(u.role);
    }
    public int hasCode(){
        return Objects.hash(name,role);
    }
}
