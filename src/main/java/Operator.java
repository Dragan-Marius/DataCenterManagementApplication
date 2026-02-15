package main.java;

public class Operator extends User{
    private String department;
    public Operator(String name, String role, String email,String department){
        super(name,role,email);
        this.department=department;
    }
}
