package com.supcrm.xinlin.supcrm.Dao;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xinlin on 16/6/13.
 */
public class LinkMan {
    int id;
    String name;
    String tel;
    String qq;
    String company;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getQQ() {
        return qq;
    }

    public void setQQ(String tel2) {
        this.qq = tel2;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString(){
        return name;
    }


}
