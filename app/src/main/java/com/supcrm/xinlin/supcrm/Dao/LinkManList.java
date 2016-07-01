package com.supcrm.xinlin.supcrm.Dao;

import android.content.Context;
import android.text.Editable;

import java.util.ArrayList;

/**
 * Created by xinlin on 16/6/13.
 */
public class LinkManList {
    private static LinkManList sLinkList;
    private Context mAppContext;

    private ArrayList<LinkMan> mLinkMans;

    private LinkManList(Context a){
        mAppContext=a;
        mLinkMans = new ArrayList<LinkMan>();
        for(int i=0;i<15;i++){
            LinkMan c=new LinkMan();
            c.setId(i);
            c.setName("link#"+i);
            c.setTel("123-"+i);
            c.setQQ("123-"+i);
            c.setCompany("Company#"+(i%5));
            mLinkMans.add(c);

        }
    }

    public static LinkManList get(Context a){
        if(sLinkList==null){
            sLinkList = new LinkManList(a);
        }
        return sLinkList;
    }

    public ArrayList<LinkMan> getLinkMans(){
        return mLinkMans;
    }

    public LinkMan getLinkMan(int id){
        for(LinkMan i:mLinkMans){
            if(id==i.getId()){
                return i;
            }
        }
        return null;
    }

    public void del(int linkId) {
        for(int j=0;j<getSize();j++){
            LinkMan i=mLinkMans.get(j);
            if(linkId==i.getId()){
                mLinkMans.remove(i);
            }
        }
    }

    public void store(int linkId, String name, String address, String tel, String qq) {
        for(LinkMan i:mLinkMans){
            if(linkId==i.getId()){
                i.setName(name.trim());
                i.setTel(tel.trim());
                i.setQQ(qq.trim());
                i.setCompany(address.trim());
            }
        }
    }

    public int add(String name, String address, String tel, String qq) {
        LinkMan c=new LinkMan();
        int id=getSize()+1;
        c.setId(id);
        c.setName(name.trim());
        c.setTel(tel.trim());
        c.setQQ(qq.trim());
        c.setCompany(address.trim());
        mLinkMans.add(c);
        System.out.println("all:"+String.valueOf(id));
        return id;
    }


    private int getSize() {
        return mLinkMans.size();
    }


    public void changeList(ArrayList<LinkMan> list) {
        int num=list.size();
        if(num>mLinkMans.size()){
            num=mLinkMans.size();
        }
        for(int i=0;i<num;i++){
            LinkMan b=list.get(i);
            mLinkMans.get(i).setName(b.getName());
        }
    }
}
