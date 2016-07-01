package com.supcrm.xinlin.supcrm.Dao;

import java.util.Date;

/**
 * Created by xinlin on 16/6/23.
 */
public class CustProcess {
    int id;
    Date date;
    String content;
    String stage;

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

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
