package com.supcrm.xinlin.supcrm.Dao;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by xinlin on 16/6/23.
 */
public class CommodityList {
    private static CommodityList list;
    private Context mcontext;
    private ArrayList<Commodity> commodities;

    private CommodityList(Context context){
        mcontext=context;
        commodities=new ArrayList<Commodity>();
        for(int i=0;i<15;i++){
            Commodity c=new Commodity();
            c.setId(i);
            c.setName("联想－"+i);
            c.setKind("电脑");
            c.setInfo("联想电脑，品质的保障，就是好,重要的事说3遍；联想电脑，品质的保障，就是好,重要的事说3遍；联想电脑，品质的保障，就是好,重要的事说3遍；联想电脑，品质的保障，就是好,重要的事说3遍");
            c.setNum(100);
            double a=3000+ 44.6*i;
            c.setPrice(a);

            commodities.add(c);
        }
    }

    public static CommodityList get(Context a){
        if(list==null){
            list = new CommodityList(a);
        }
        return list;
    }

    public ArrayList<Commodity> getCommodity() {
        return commodities;
    }

    public Commodity getCommodity(int in){
        for(Commodity i:commodities){
            if (i.getId()==in){
                return i;
            }
        }
        return null;
    }

    public void store(int commodityId, String name, String kind, String price, String num, String info) {
        for(Commodity i:commodities){
            if (i.getId()==commodityId){
                i.setName(name);
                i.setKind(kind);
                i.setInfo(info);
                i.setNum(Integer.parseInt(num));
                i.setPrice(Double.parseDouble(price));
                break;
            }
        }
    }

    public void del(int commodityId) {
        for(int i=0;i<getSize();i++){
            Commodity temp=commodities.get(i);
            if(temp.getId()==commodityId){
                commodities.remove(temp);
                break;
            }
        }
    }

    private int getSize() {
        return commodities.size();
    }

    public void add(String name, String kind, String price, String num, String info) {
        int id=getSize()+1;
        Commodity temp=new Commodity();
        temp.setId(id);
        temp.setName(name);
        temp.setKind(kind);
        temp.setInfo(info);
        temp.setNum(Integer.parseInt(num));
        temp.setPrice(Double.parseDouble(price));
        commodities.add(temp);
    }

    public void changelist(ArrayList<Commodity> list) {
        int num=list.size();
        if(num>commodities.size()){
            num=commodities.size();
        }
        for(int i=0;i<num;i++){
            Commodity b=list.get(i);
            commodities.get(i).setName(b.getName());
        }
    }

}
