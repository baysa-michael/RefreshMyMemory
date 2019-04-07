package com.refreshmymemory.model;

public class UserAccount {
    private String username;
    private String hashedPassword;
    private String email;
    private String displayName;
    private int userSalt;

    public UserAccount(String username,
                       String hashedPassword,
                       String email,
                       String displayName,
                       int userSalt) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.email = email;
        this.displayName = displayName;
        this.userSalt = userSalt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getUserSalt() {
        return userSalt;
    }

    public void setUserSalt(int userSalt) {
        this.userSalt = userSalt;
    }
}
