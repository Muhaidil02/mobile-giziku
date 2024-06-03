package com.android.giziku.models;

public class UserModel {
    String Username, email,password,passwordLagi;

    public UserModel() {
    }

    public UserModel(String username, String email, String password, String passwordLagi) {
        Username = username;
        this.email = email;
        this.password = password;
        this.passwordLagi = passwordLagi;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordLagi() {
        return passwordLagi;
    }

    public void setPasswordLagi(String passwordLagi) {
        this.passwordLagi = passwordLagi;
    }
}
