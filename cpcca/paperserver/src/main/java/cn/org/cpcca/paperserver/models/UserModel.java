package cn.org.cpcca.paperserver.models;

import java.io.Serializable;
import java.util.List;

public class UserModel implements Serializable {
    private static final  long serialVersionUID = 1L;
    private int id;
    private String username;
    private String password;
    private String password_salt;
    private int flag;
    private List<String> roleStrlist;
    private List<String> perminsStrlist;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getPassword_salt() {
        return password_salt;
    }

    public void setPassword_salt(String password_salt) {
        this.password_salt = password_salt;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public List<String> getRoleStrlist() {
        return roleStrlist;
    }

    public void setRoleStrlist(List<String> roleStrlist) {
        this.roleStrlist = roleStrlist;
    }

    public List<String> getPerminsStrlist() {
        return perminsStrlist;
    }

    public void setPerminsStrlist(List<String> perminsStrlist) {
        this.perminsStrlist = perminsStrlist;
    }
}
