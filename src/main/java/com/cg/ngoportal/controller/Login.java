package com.cg.ngoportal.controller;

 

public class Login {
    private String username;
    private String  password;
    
    public Login() {
        // TODO Auto-generated constructor stub
    }
    
    
    public Login(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

 


    @Override
    public String toString() {
        return "Login [username=" + username + ", password=" + password + "]";
    }
    
    

 

}