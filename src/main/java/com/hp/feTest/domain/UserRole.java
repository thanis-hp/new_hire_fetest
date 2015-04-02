package com.hp.feTest.domain;


public class UserRole {
    
    private Integer userRoleId;
    
    private Integer userId;
    
    private String authority;

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRole)) return false;

        UserRole userRole = (UserRole) o;

        if (!authority.equals(userRole.authority)) return false;
        if (!userId.equals(userRole.userId)) return false;
        if (!userRoleId.equals(userRole.userRoleId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userRoleId.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + authority.hashCode();
        return result;
    }
}
