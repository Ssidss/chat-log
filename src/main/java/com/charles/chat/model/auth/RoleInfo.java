package com.charles.chat.model.auth;

import javax.persistence.*;

@Entity(name = "role_info")
@Table(name = "role_info")
public class RoleInfo {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public RoleInfo setId(String id) {
        this.id = id;
        return this;
    }


}
