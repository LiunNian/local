package com.paper.common.model;

import org.apache.shiro.authc.UsernamePasswordToken;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public class User extends UsernamePasswordToken implements Serializable{

    private static final long serialVersionUID = -7484136779753770396L;
    private String id;

    private String username;

    private String companyId;

    Set<String> roleSet;

    private Integer state;

    private String loginType;

    private Map map;

    public User(){}


    public User(String id, String username, String companyId, Integer state) {
        this.id = id;
        this.username = username;
        this.companyId = companyId;
        this.state = state;
    }

    public User(String username , String companyId, Integer state) {
        this.username = username;
        this.companyId = companyId;
        this.state = state;
    }

    public User(String username, Integer state) {
        this.username = username;
        this.state = state;
    }

    public User(String username, String password ) {
        super(username, password);
    }

    public User(String username, String password, String loginType) {
        super(username, password);
        this.loginType = loginType;
    }

    public User(String id, String username , String companyId, Set<String> roleSet, Integer state) {
        this.id = id;
        this.username = username;
        this.companyId = companyId;
        this.roleSet = roleSet;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Set<String> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<String> roleSet) {
        this.roleSet = roleSet;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}