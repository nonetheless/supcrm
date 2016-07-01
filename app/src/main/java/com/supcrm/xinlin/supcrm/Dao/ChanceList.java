package com.supcrm.xinlin.supcrm.Dao;

import android.content.Context;

import com.supcrm.xinlin.supcrm.Data.MockData;

import java.util.ArrayList;

/**
 * Created by xinlin on 16/6/23.
 */
public class ChanceList {
    private static ChanceList mList;
    private static int isAll =0;

    private Context mAppContext;
    private ArrayList<Chance> mChances;

    private ChanceList(Context context){
        mAppContext = context;
        MockData a=new MockData();
        mChances = a.getChances();


    }

    public static ChanceList get(Context a){
        if(null==mList){
            mList=new ChanceList(a);
        }
        return mList;
    }


    public ArrayList<Chance> getByCust(int custid) {
        ArrayList<Chance> back=new ArrayList<Chance>();
        for(Chance m:mChances){
            if(m.getCustId()==custid){
                back.add(m);
            }
        }
        return back;
    }

    public Chance getOne(int id) {
        for(Chance i:mChances){
            if(id==i.getId()){
                return i;
            }
        }
        return null;
    }

    public void delProcess( int chanceId,int processId) {
        for(int i=0;i<mChances.size();i++){
            Chance tem=mChances.get(i);
            if(chanceId==tem.getId()){
                ArrayList<ChanceProcess> processes=tem.getList();
                for(int j=0;j<processes.size();j++){
                    ChanceProcess p=processes.get(j);
                    if(p.getId()==processId){
                        mChances.remove(tem);
                        processes.remove(j);
                        tem.setList(processes);
                        mChances.add(tem);
                        break;
                    }
                }
            }
        }

    }

    public int store(int id, ChanceProcess temp) {
        for(int i=0;i<mChances.size();i++){
            Chance temp1=mChances.get(i);
            if(id==temp1.getId()){
                mChances.remove(temp1);
                ArrayList<ChanceProcess> processes=temp1.getList();
                int num =processes.size();
                temp.setId(num);
                processes.add(temp);
                temp1.setList(processes);
                mChances.add(temp1);
                break;
            }
        }
        return 1;
    }

    public void upd(Chance chance) {
        for(int i=0;i<mChances.size();i++){
            Chance temp=mChances.get(i);
            if(temp.getId()==chance.getId()){
                mChances.remove(temp);
                mChances.add(chance);
                return;
            }
        }
    }

    public void delChance(int chanceid) {
        for(int i=0;i<mChances.size();i++){
            Chance temp=mChances.get(i);
            if(temp.getId()==chanceid){
                mChances.remove(temp);
                return;
            }
        }
    }

    public  void add(Chance a) {
        if(a.getCommodityId()<0){
            return;
        }
        a.setId(mChances.size()+1);
        a.setList(new ArrayList<ChanceProcess>());
        mChances.add(a);
        System.out.println("gggCom1:"+String.valueOf(a.getCommodityId()));
    }
}
