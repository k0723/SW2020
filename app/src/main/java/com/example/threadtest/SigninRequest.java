package com.example.threadtest;

public class SigninRequest {

    private String userId;

    private String userName;

    private String userPassword;

    private String userdeviceId;

    private String useremail;

    public SigninRequest(String userId,
                         String userName,
                         String userPassword,
                         String userdeviceId,
                         String useremail) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userdeviceId = userdeviceId;
        this.useremail = useremail;
    }

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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserdeviceId() {
        return userdeviceId;
    }

    public void setUserdeviceId(String userdeviceId) {
        this.userdeviceId = userdeviceId;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }
}
