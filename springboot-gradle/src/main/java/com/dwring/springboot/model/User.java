package com.dwring.springboot.model;
import java.io.Serializable;
/**
 * Created by zhanghaichang on 2017/8/3.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 8809101560720973267L;

    private Integer id;

    private String userName;


    private String password;

    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", password="
                + password + ", age=" + age + "]";
    }


}
