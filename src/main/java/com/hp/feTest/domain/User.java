package com.hp.feTest.domain;

import java.io.Serializable;

public class User implements Serializable {

    private Integer userId;
    
    private String userName;
    
    private String password;

    private Integer enabled;
    
    private UserRole userRole;
    
    private String name;

    private String dept;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!dept.equals(user.dept)) return false;
        if (!enabled.equals(user.enabled)) return false;
        if (!name.equals(user.name)) return false;
        if (!password.equals(user.password)) return false;
        if (!userId.equals(user.userId)) return false;
        if (!userName.equals(user.userName)) return false;
        if (!userRole.equals(user.userRole)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + userName.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + enabled.hashCode();
        result = 31 * result + userRole.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + dept.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", userRole=" + userRole +
                ", name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                '}';
    }
}
