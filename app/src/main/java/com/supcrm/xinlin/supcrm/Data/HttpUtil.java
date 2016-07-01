package com.supcrm.xinlin.supcrm.Data;


import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by YJF on 2016/6/26.
 */


public class HttpUtil {

    public static String startDownload(final String adress, final String param){

        HttpURLConnection conn=null;
        String back;
        try {
            URL url = new URL(adress);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(8000);
            conn.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(param);
            InputStream in = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder res = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                res.append(line);
            }
            back=res.toString();
            out.close();
            in.close();
            reader.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
            back="";
        }
        return back;

    }
    public static void sendGetRequest(final String adress,final HttpCallbackListener listener,final String param){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn=null;
                try{
                    URL url=new URL(adress);
                    conn=(HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    conn.setDoOutput(true);
                    DataOutputStream out=new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(param);
                    InputStream in=conn.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                    StringBuilder res=new StringBuilder();
                    String line;
                    while((line=reader.readLine())!=null) {
                        res.append(line);
                    }
                    if(listener!=null){
                        listener.onFinish(res.toString());
                    }
                }catch(Exception e){
                    if(listener!=null){
                        listener.onError(e);
                    }
                }finally {
                    if(conn!=null) {
                        conn.disconnect();
                    }
                }
            }
        }).start();
    }

    public static void sendPostRequest(final String adress,final HttpCallbackListener listener,final String param){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn=null;
                try{
                    URL url=new URL(adress);
                    conn=(HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    conn.setDoOutput(true);
                    DataOutputStream out=new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(param);
                    InputStream in=conn.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                    StringBuilder res=new StringBuilder();
                    String line;
                    while((line=reader.readLine())!=null) {
                        res.append(line);
                    }
                    if(listener!=null){
                        listener.onFinish(res.toString());
                    }
                }catch(Exception e){
                    if(listener!=null){
                        listener.onError(e);
                    }
                }finally {
                    if(conn!=null) {
                        conn.disconnect();
                    }
                }
            }
        }).start();
    }
}
