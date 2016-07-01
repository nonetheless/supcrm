package com.supcrm.xinlin.supcrm.Data;

import com.supcrm.xinlin.supcrm.Dao.Chance;
import com.supcrm.xinlin.supcrm.Dao.ChanceProcess;
import com.supcrm.xinlin.supcrm.Dao.Contract;
import com.supcrm.xinlin.supcrm.Dao.CustProcess;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by xinlin on 16/6/24.
 */
public class MockData {

    public ArrayList<CustProcess> getCustProcess() {
        ArrayList<CustProcess> process=new ArrayList<CustProcess>();
        for(int i=0;i<4;i++){
            CustProcess temp=new CustProcess();
            temp.setId(i);
            temp.setDate(new Date());
            temp.setStage("阶段#"+i);
            temp.setContent("和用户见面，对商机的共识加大");
            process.add(temp);
        }
        return process;
    }

    public ArrayList<Chance> getChance(int id) {
        ArrayList<Chance> chance =new ArrayList<Chance>();
        for(int i=id*4;i<id*4+4;i++){
            Chance temp=new Chance();
            temp.setId(i);
            temp.setName("商机#"+i);
            temp.setCommodityId(id);
            temp.setCustId(id);
            temp.setList(getChanceProcess());
            chance.add(temp);
        }
        return chance;
    }

    public ArrayList<ChanceProcess> getChanceProcess() {
        ArrayList<ChanceProcess> process=new ArrayList<ChanceProcess>();
        for(int i=0;i<4;i++){
            ChanceProcess temp=new ChanceProcess();
            temp.setDate(new Date());
            temp.setName("商机探寻#"+i);
            temp.setContent("初步接触对方，进一步交流");
            process.add(temp);
        }
        return process;
    }

    public static ArrayList<Contract> getContracts() {
        ArrayList<Contract>list =new ArrayList<Contract>();
        for(int id=0;id<10;id++) {
            for (int i = 0; i < 3; i++) {
                Contract contract = new Contract();
                int d=id*3+i;
                contract.setName("合同#"+d);
                contract.setContractId(id * 3 + i);
                contract.setCommodityId(i+id);
                contract.setDate(new Date());
                contract.setCustomerName("客户#" + id);
                contract.setCustomerId(id);
                contract.setNum(id * 10 + i + 34);
                contract.setSaler("xinlin");
                list.add(contract);
            }
        }
        return list;
    }

    public ArrayList<Chance> getChances() {
        ArrayList<Chance> back=new ArrayList<Chance>();
        for(int id=0;id<10;id++){
            for(int i=0;i<2;i++){
                Chance a=new Chance();
                int tid=id*2+i;
                a.setId(tid);
                a.setName("商机＃"+tid);
                a.setCommodityId(tid%5);
                a.setCustId(id);
                a.setInfo("英国都离开欧盟了，这个卖到欧盟去绝对赚");
                ArrayList<ChanceProcess> list = getlist();
                a.setList(list);
                a.setSaler("小二王");
                back.add(a);
            }
        }
        return back;

    }

    private ArrayList<ChanceProcess> getlist() {
        ArrayList<ChanceProcess> list=new ArrayList<ChanceProcess>();
        for (int i=0;i<3;i++) {
            ChanceProcess a = new ChanceProcess();
            a.setDate(new Date());
            a.setName("王小二");
            a.setContent("今天去调查了外卖情况，下雨天外卖多");
            a.setId(i);
            list.add(a);
        }
        return list;
    }
}
