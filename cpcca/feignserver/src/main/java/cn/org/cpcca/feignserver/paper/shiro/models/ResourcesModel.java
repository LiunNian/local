package cn.org.cpcca.feignserver.paper.shiro.models;

import java.io.Serializable;

public class ResourcesModel implements Serializable {
    private String url = ""; //地址
    private String roles; //所需要的角色，可省略
    private String permissions = ""; //所需要的权限，可省略

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}
