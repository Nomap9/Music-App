package com.example.musicapp.Models;

import java.io.Serializable;

public class User implements Serializable {

    private Boolean status;
    private String id;
    private String firstname;
    private  String lastname;
    private String emailUser;
    private Boolean admin ;
    private String createdAt;
    private String updateAt;

    public User () {

    }

    public User( Boolean status, String id, String firstname, String lastname, String emailUser, Boolean admin, String createdAt, String updateAt) {

        this.status = status;
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailUser = emailUser;
        this.admin = admin;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }



    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "User{" +
                ", status=" + status +
                ", id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", emailUser='" + emailUser + '\'' +
                ", admin=" + admin +
                ", createdAt='" + createdAt + '\'' +
                ", updateAt='" + updateAt + '\'' +
                '}';
    }
}
