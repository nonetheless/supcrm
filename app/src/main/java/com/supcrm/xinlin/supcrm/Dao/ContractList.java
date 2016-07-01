package com.supcrm.xinlin.supcrm.Dao;

import android.content.Context;

import com.supcrm.xinlin.supcrm.Data.MockData;

import java.util.ArrayList;

/**
 * Created by xinlin on 16/6/23.
 */
public class ContractList {
    public static ContractList list;

    private ArrayList<Contract> contracts;
    private Context context;

    private ContractList(Context c){
        context = c;
        contracts = MockData.getContracts();
    }

    public static ContractList get(Context c){
        if(list==null){
            list=new ContractList(c);
        }
        return list;

    }

    public ArrayList<Contract> getByCust(int id) {
        ArrayList<Contract> back=new ArrayList<Contract>();
        for (Contract i:contracts){
            if(i.getCustomerId()==id){
                back.add(i);
            }
        }
        return back;

    }

    public Contract getOnContract(int id) {
        for (Contract i:contracts){
            if(i.getContractId()==id){
                return i;
            }
        }
        return null;
    }


    public void delOne(int id) {
        for(int i=0;i<contracts.size();i++){
            Contract temp=contracts.get(i);
            if(id==temp.getContractId()){
                contracts.remove(i);
                break;
            }
        }
    }

    public void upd(Contract a) {
        for(int i=0;i<contracts.size();i++){
            Contract temp=contracts.get(i);
            if(temp.getContractId()==a.getContractId()){
                contracts.remove(temp);
                contracts.add(a);
                return;
            }
        }
    }

    public void add(Contract a) {
        if(a.getContractId()==-1){
            return;
        }
        int len=contracts.size()+1;
        a.setContractId(len);
        contracts.add(a);

    }
}
