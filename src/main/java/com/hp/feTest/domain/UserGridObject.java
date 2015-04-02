package com.hp.feTest.domain;


public class UserGridObject {
    
    private String userId;
    
    private String userName;
    
    private String name;
    
    private String dept;
    
    private String role;
    
    private String enabled;
    
    private String lastLogin;
    
    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserGridObject)) return false;

        UserGridObject that = (UserGridObject) o;

        if (dept != null ? !dept.equals(that.dept) : that.dept != null) return false;
        if (lastLogin != null ? !lastLogin.equals(that.lastLogin) : that.lastLogin != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (!role.equals(that.role)) return false;
        if (!enabled.equals(that.enabled)) return false;
        if (!userName.equals(that.userName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (dept != null ? dept.hashCode() : 0);
        result = 31 * result + role.hashCode();
        result = 31 * result + enabled.hashCode();
        result = 31 * result + (lastLogin != null ? lastLogin.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserGridObject{" +
                "lastLogin='" + lastLogin + '\'' +
                ", enabled='" + enabled + '\'' +
                ", role='" + role + '\'' +
                ", dept='" + dept + '\'' +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}


