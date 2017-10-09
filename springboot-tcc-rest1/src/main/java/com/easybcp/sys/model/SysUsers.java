package com.easybcp.sys.model;

import java.util.Date;

public class SysUsers {
    private Long id;

    private String username;

    private String password;

    private String usersex;

    private String nickname;

    private Date agentdeadline;

    private Date createddatetime;

    private Date updateddatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsersex() {
        return usersex;
    }

    public void setUsersex(String usersex) {
        this.usersex = usersex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getAgentdeadline() {
        return agentdeadline;
    }

    public void setAgentdeadline(Date agentdeadline) {
        this.agentdeadline = agentdeadline;
    }

    public Date getCreateddatetime() {
        return createddatetime;
    }

    public void setCreateddatetime(Date createddatetime) {
        this.createddatetime = createddatetime;
    }

    public Date getUpdateddatetime() {
        return updateddatetime;
    }

    public void setUpdateddatetime(Date updateddatetime) {
        this.updateddatetime = updateddatetime;
    }
}