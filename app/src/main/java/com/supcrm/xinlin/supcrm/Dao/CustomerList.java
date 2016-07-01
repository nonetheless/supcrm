package com.supcrm.xinlin.supcrm.Dao;

import android.content.Context;
import android.provider.Settings;

import com.supcrm.xinlin.supcrm.Data.MockData;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by xinlin on 16/6/21.
 */
public class CustomerList {
    private static CustomerList list;
    private Context context;
    private ArrayList<Customer> customers;

    private CustomerList(Context c){
        context=c;
        customers=new ArrayList<Customer>();
        String[] name={"wang","未有","ewew","奇艺","ee","111","送快递","呃呃","dsllf","ewe","","ekke"};
        for(int i=0;i<10;i++){
            Customer temp=new Customer();
            temp.setId(i);
            temp.setLinkMan_id(i);
            temp.setName(name[i]);
            MockData dataget=new MockData();
            ArrayList<Contract> contract=ContractList.get(null).getByCust(i);
            ArrayList<Chance> chance =dataget.getChance(i);
            ArrayList<CustProcess> process=dataget.getCustProcess();
            temp.setChance(chance);
            temp.setContract(contract);
            temp.setProcesses(process);
            customers.add(temp);

        }
    }

    public static CustomerList get(Context c){
        if(list==null){
            list=new CustomerList(c);
        }
        return list;

    }

    public ArrayList<Customer> getCustomer() {
        return customers;
    }

    public Customer getOneCustomer(int linkId) {
        for(Customer i:customers){
            if(linkId==i.getId()){
                return i;
            }
        }
        return null;
    }

    public int store(int id, CustProcess temp) {
        for(int i=0;i<customers.size();i++){
            Customer temp1=customers.get(i);
            if(id==temp1.getId()){
                customers.remove(temp1);
                ArrayList<CustProcess> processes=temp1.getProcesses();
                int num =processes.size();
                temp.setId(num);
                processes.add(temp);
                temp1.setProcesses(processes);
                customers.add(temp1);
                break;
            }
        }
        return 1;
    }

    public void delProcess(int id,int num) {
        for(int i=0;i<customers.size();i++){
            Customer tem=customers.get(i);
            if(id==tem.getId()){
                ArrayList<CustProcess> processes=tem.getProcesses();
                for(int j=0;j<processes.size();j++){
                    CustProcess p=processes.get(j);
                    if(p.getId()==num){
                        customers.remove(tem);
                        processes.remove(j);
                        tem.setProcesses(processes);
                        customers.add(tem);
                        break;
                    }
                }
            }
        }
    }

    public int addCust(String s, int linkId) {
        int i=customers.size()+1;
        Customer temp=new Customer();
        temp.setId(i);
        temp.setLinkMan_id(linkId);
        temp.setName(s);
        MockData dataget=new MockData();
        ArrayList<Contract> contract=new ArrayList<Contract>();
        ArrayList<Chance> chance =new ArrayList<Chance>();
        ArrayList<CustProcess> process=new ArrayList<CustProcess>();
        temp.setChance(chance);
        temp.setContract(contract);
        temp.setProcesses(process);
        customers.add(temp);
        return i;
    }
}
