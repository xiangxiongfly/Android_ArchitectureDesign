package com.example.mvvm.bean;

public class User {
    private boolean admin;
    private int id;
    private String nickname;
    private String publicName;
    private String username;

    public User() {
    }

    public User(boolean admin, int id, String nickname, String publicName, String username) {
        this.admin = admin;
        this.id = id;
        this.nickname = nickname;
        this.publicName = publicName;
        this.username = username;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "admin=" + admin +
                ", id=" + id +
                ", nickname='" + nickname + '\'' +
                ", publicName='" + publicName + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
