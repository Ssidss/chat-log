package com.charles.chat.model;

import javax.persistence.*;

@Table(name = "join_user_info"
)
@Entity(name = "join_user_info")
public class JoinUserInfo {

    @Id
    private String id;

    @Column(name = "role_id")
    private String roleId;

    public String getId() {
        return id;
    }

    public JoinUserInfo setId(String id) {
        this.id = id;
        return this;
    }

    public String getRoleId() {
        return roleId;
    }

    public JoinUserInfo setRoleId(String roleId) {
        this.roleId = roleId;
        return this;
    }
}
