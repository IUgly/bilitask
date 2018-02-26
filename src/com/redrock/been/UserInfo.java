package com.redrock.been;

import java.io.Serializable;
import java.util.Map;

/**
 * @author ugly
 */
public class UserInfo implements Serializable{

    private static final long serialVersionUID = 1L;

    private String username;
    private String pswd;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    @Override
    public String toString() {
        return "UserInfo [ username=" + username + ", pswd="
                + pswd + "]";
    }
}
