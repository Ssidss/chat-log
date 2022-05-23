package com.charles.chat.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "daily_music")
@Entity(name = "daily_music")
public class DailyMusic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    @Column(name = "shared_user")
    private String sharedUser;
    @Column(name = "join_code")
    private String joinCode;
    @Column(name = "create_at")
    private Date createAt;
    private Integer score;

    public Long getId() {
        return id;
    }

    public DailyMusic setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public DailyMusic setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getSharedUser() {
        return sharedUser;
    }

    public DailyMusic setSharedUser(String sharedUser) {
        this.sharedUser = sharedUser;
        return this;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public DailyMusic setCreateAt(Date createAt) {
        this.createAt = createAt;
        return this;
    }

    public Integer getScore() {
        return score;
    }

    public DailyMusic setScore(Integer score) {
        this.score = score;
        return this;
    }

    public String getJoinCode() {
        return joinCode;
    }

    public DailyMusic setJoinCode(String joinCode) {
        this.joinCode = joinCode;
        return this;
    }
}
