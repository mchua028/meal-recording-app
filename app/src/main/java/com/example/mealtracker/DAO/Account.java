package com.example.mealtracker.DAO;


import com.example.mealtracker.Exceptions.RegisterError;

/**
 * DAO about account
 */
public class Account {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String salt;
    private String verificationCode;
    private String userId;

    public Account(){}

    public Account(String email, String firstName, String lastName, String password){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.password=password;
    }

    public String getUsername(){
        return username;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean verifyAccountForLogin() {
        boolean isVerified=false;
        return isVerified;
    }
}
