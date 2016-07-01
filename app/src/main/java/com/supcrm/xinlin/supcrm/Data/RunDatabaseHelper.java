package com.supcrm.xinlin.supcrm.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xinlin on 16/6/23.
 */
public class RunDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME ="surp.splite";
    private static final int VERSION = 1;

    private static final String TABLE_LINK = "linkman";
    private static final String COLUM_RUN_START_DATE="start_date";

    public RunDatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table linkman (_id integer primary key autoincrement,link_id integer,link_name varchar(100),link_tel varchar(100),link_qq varchar(100),link_company varchar(100))");
        db.execSQL("create table commodity ( _id integer primary key autoincrement,commodity_id integer,commodity_name varchar(100),commodity_info varchar(100),commodity_num integer,price double)");
        db.execSQL("create table chance ( _id integer primary key autoincrement,chance_id integer,customer_id integer references customer(customer_id),commodity_id integer references commodity(commodity_id))");
        db.execSQL("create table chance_process (_id integer primary key autoincrement,chance_process_id integer,chance_id integer references chance(chance_id),chance_date date,chance_info varchar(100))");
        db.execSQL("create table customer (_id integer primary key autoincrement,customer_id integer,customer_name varchar(100),linkman_id integer references linkman(link_id),chance_id integer references chance(chance_id),contract_id integer references contract(contract_id),process_id integer references cust_process(process_id))\n");
        db.execSQL("create table cust_process (_id integer primary key autoincrement,process_id integer,chance_date date,chance_info varchar(100))");
        db.execSQL("create table contract (_id integer primary key autoincrement,contract_id integer,commodity_id integer,customer_id integer references customer(customer_id,saler_name varchar(100),num integer,contract_time date)");

        /*
        create table linkman (_id integer primary key autoincrement,link_id integer,link_name varchar(100),link_tel varchar(100),link_qq varchar(100),link_company varchar(100))
create table commodity ( _id integer primary key autoincrement,commodity_id integer,commodity_name varchar(100),commodity_info varchar(100),commodity_num integer,price double)
create table chance ( _id integer primary key autoincrement,chance_id integer,customer_id integer references customer(customer_id),commodity_id integer references commodity(commodity_id))
create table chance_process (_id integer primary key autoincrement,chance_process_id integer,chance_id integer references chance(chance_id),chance_date date,chance_info varchar(100))
create table customer (_id integer primary key autoincrement,customer_id integer,customer_name varchar(100),linkman_id integer references linkman(link_id),chance_id integer references chance(chance_id),contract_id integer references contract(contract_id),process_id integer references cust_process(process_id))
create table cust_process (_id integer primary key autoincrement,process_id integer,chance_date date,chance_info varchar(100))
create table contract (_id integer primary key autoincrement,contract_id integer,commodity_id integer,customer_id integer references customer(customer_id),saler_name varchar(100),num integer,contract_time date)
         */

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
