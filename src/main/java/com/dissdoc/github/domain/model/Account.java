package com.dissdoc.github.domain.model;

/**
 * Created by KMukhov on 21.07.2015.
 */
public class Account {

    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public Account(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
