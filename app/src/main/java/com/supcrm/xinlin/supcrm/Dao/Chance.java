package com.supcrm.xinlin.supcrm.Dao;

import java.util.ArrayList;

/**
 * Created by xinlin on 16/6/21.
 */
public class Chance {
    int id;
    String name;
    int custId;
    int commodityId;
    String info;
    ArrayList<ChanceProcess> list;
    String saler;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public ArrayList<ChanceProcess> getList() {
        return list;
    }

    public void setList(ArrayList<ChanceProcess> list) {
        this.list = list;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSaler() {
        return saler;
    }

    public void setSaler(String saler) {
        this.saler = saler;
    }

    @Override
    public String toString(){
        return name;
    }
}
