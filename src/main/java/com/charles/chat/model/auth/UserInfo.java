package com.charles.chat.model.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity(name = "user_info")
@Table(name = "user_info")
public class UserInfo {

    @Id
    private String id;

    private String password;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "login_time")
    private Integer loginTime;

    @Column(name = "last_login")
    private Date lastLogin;

    @Column(name = "last_use_token")
    private String lastUseToken;

    public String getId() {
        return id;
    }

    public UserInfo setId(String id) {
        this.id = id;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserInfo setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserInfo setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public Integer getLoginTime() {
        return loginTime;
    }

    public UserInfo setLoginTime(Integer loginTime) {
        this.loginTime = loginTime;
        return this;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public UserInfo setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
        return this;
    }

    public String getLastUseToken() {
        return lastUseToken;
    }

    public UserInfo setLastUseToken(String lastUseToken) {
        this.lastUseToken = lastUseToken;
        return this;
    }
}
