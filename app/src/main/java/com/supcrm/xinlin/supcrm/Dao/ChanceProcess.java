package com.supcrm.xinlin.supcrm.Dao;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by xinlin on 16/6/23.
 */
public class ChanceProcess {
    int id;
    Date date;
    String content;
    String name;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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
}
