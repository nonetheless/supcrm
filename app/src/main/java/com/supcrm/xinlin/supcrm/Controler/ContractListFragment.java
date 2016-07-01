package com.supcrm.xinlin.supcrm.Controler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.supcrm.xinlin.supcrm.Dao.Contract;
import com.supcrm.xinlin.supcrm.Dao.ContractList;
import com.supcrm.xinlin.supcrm.Dao.Customer;
import com.supcrm.xinlin.supcrm.Dao.CustomerList;
import com.supcrm.xinlin.supcrm.R;

import java.util.ArrayList;

public class ContractListFragment extends ListFragment {
    public static final String EXTRA_Cust_ID = "com.supcrm.xinlin.customer_id";

    private ArrayList<Contract> contracts;
    private ArrayAdapter<Contract> adapter;
    int custid;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.customerin,menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.action_contract:
                Intent i=new Intent(getActivity(),ContractAddActivity.class);
                i.putExtra(EXTRA_Cust_ID,custid);
                startActivityForResult(i,0);
                return true;
            default:
                return false;
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        custid = (int)getActivity().getIntent().getSerializableExtra(EXTRA_Cust_ID);
        contracts= ContractList.get(getActivity()).getByCust(custid);
        adapter=new ArrayAdapter<Contract>(getActivity(),android.R.layout.simple_list_item_1,contracts);
        setListAdapter(adapter);
        Customer one= CustomerList.get(getActivity()).getOneCustomer(custid);
        getActivity().setTitle(one.getName());
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){

        Contract contract = (Contract) (getListAdapter()).getItem(position);
        Log.d("id", String.valueOf(contract.getContractId()));

        Fragment newFragment = ContractFragment.newInstance(contract.getContractId());

        FragmentTransaction transaction =getFragmentManager().beginTransaction();
        newFragment.setTargetFragment(ContractListFragment.this,0);
        //dialog.setTargetFragment(CustProcessAddFragment.this,Request_DATA);
        //dialog.show(fm,"date");

        transaction.replace(R.id.fragment_container,newFragment);
        transaction.addToBackStack(null);

        transaction.commit();

    }

    @Override
    public void onResume(){
        super.onResume();
        contracts= ContractList.get(getActivity()).getByCust(custid);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int req, int rest, Intent data){

        FragmentTransaction fmt = getActivity().getSupportFragmentManager().beginTransaction();
        FragmentManager fm=getActivity().getSupportFragmentManager();
        Fragment back=fm.findFragmentById(R.id.fragment_container);
        fmt.remove(back);
        Fragment t=new ContractListFragment();
        fmt.add(R.id.fragment_container, t);
        fmt.commit();
    }

    public static Fragment newInstance() {
        return new ContractListFragment();
    }

}
