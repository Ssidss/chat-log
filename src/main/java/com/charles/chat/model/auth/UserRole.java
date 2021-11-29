package com.charles.chat.model.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "user_role")
@Entity(name = "user_role")
public class UserRole implements Serializable {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Id
    @Column(name = "role_id")
    private String roleId;

    public String getUserId() {
        return userId;
    }

    public UserRole setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getRoleId() {
        return roleId;
    }

    public UserRole setRoleId(String roleId) {
        this.roleId = roleId;
        return this;
    }
}
