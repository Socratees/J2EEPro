package entity;

import java.io.Serializable;

public class User implements Serializable{
    private int userid;
    private String username;
    private String password;

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public int getUserId(){
        return userid;
    }
}
