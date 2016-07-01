package com.supcrm.xinlin.supcrm.Dao;

/**
 * Created by xinlin on 16/6/12.
 */
public class UserList {
    private static Users user;
    private static UserList single;
    private UserList(Users u){
        user=u;
    }
    public static UserList getSingle(Users a){
        single =new UserList(a);
        return single;
    }

    public static UserList get(){
        return single;
    }

    public Users getUser(){
        return user;
    }




}
