package com.supcrm.xinlin.supcrm.Dao;

import java.util.ArrayList;

/**
 * Created by xinlin on 16/6/21.
 */
public class Customer {
    String name;
    int id;
    int linkMan_id;
    ArrayList<Contract> contract;
    ArrayList<Chance> chance;
    ArrayList<CustProcess> processes;


    public ArrayList<CustProcess> getProcesses() {
        return processes;
    }

    public void setProcesses(ArrayList<CustProcess> processes) {
        this.processes = processes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLinkMan_id() {
        return linkMan_id;
    }

    public void setLinkMan_id(int linkMan_id) {
        this.linkMan_id = linkMan_id;
    }

    public ArrayList<Contract> getContract() {
        return contract;
    }

    public void setContract(ArrayList<Contract> contract) {
        this.contract = contract;
    }

    public ArrayList<Chance> getChance() {
        return chance;
    }

    public void setChance(ArrayList<Chance> chance) {
        this.chance = chance;
    }

    @Override
    public String toString(){
        return name;
    }
}
