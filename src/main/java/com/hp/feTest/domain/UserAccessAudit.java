package com.hp.feTest.domain;

import java.io.Serializable;
import java.util.Date;


public class UserAccessAudit  implements Serializable {


    private String userId;
    
    private String sessionId;
    
    private Date dateTime;

    private String strDateTime;
    
    private String actionType;

    private User user;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
        //setStrDateTime();
    }

    public String getStrDateTime() {
        return strDateTime;
    }

    public void setStrDateTime(String strDateTime) {
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        sdf.setLenient(false);
        this.strDateTime = sdf.format(dateTime);*/
        this.strDateTime = strDateTime;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccessAudit)) return false;

        UserAccessAudit that = (UserAccessAudit) o;

        if (!actionType.equals(that.actionType)) return false;
        if (!dateTime.equals(that.dateTime)) return false;
        if (!sessionId.equals(that.sessionId)) return false;
        if (!strDateTime.equals(that.strDateTime)) return false;
        if (!user.equals(that.user)) return false;
        if (!userId.equals(that.userId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + sessionId.hashCode();
        result = 31 * result + dateTime.hashCode();
        result = 31 * result + strDateTime.hashCode();
        result = 31 * result + actionType.hashCode();
        result = 31 * result + user.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserAccessAudit{" +
                "userId='" + userId + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", dateTime=" + dateTime +
                ", strDateTime='" + strDateTime + '\'' +
                ", actionType='" + actionType + '\'' +
                ", user=" + user +
                '}';
    }
}
