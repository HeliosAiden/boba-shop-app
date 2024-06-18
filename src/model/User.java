package model;

import java.time.LocalDate;

public class User {
    private String userId, username, password, address, phone, email;
    private int role;
    private LocalDate createdAt, updatedAt;

    public User(String userId, String username, String password, String email, int role ){
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public String getUsername() {
        return this.username;
    }

    public String getUserID() {
        return this.userId;
    }

    public String getUserPassword() {
        return this.password;
    }

    public String getUserEmail() {
        return this.email;
    }

    public int getUserRole() {
        return this.role;
    }

}
