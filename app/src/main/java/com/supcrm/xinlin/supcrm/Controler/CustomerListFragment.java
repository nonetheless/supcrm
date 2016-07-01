package com.supcrm.xinlin.supcrm.Controler;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.supcrm.xinlin.supcrm.Dao.Customer;
import com.supcrm.xinlin.supcrm.Dao.CustomerList;

import java.util.ArrayList;


public class CustomerListFragment extends ListFragment {
    public static final String EXTRA_Cust_ID = "com.supcrm.xinlin.customer_id";
    public static final String EXTRA_LINKMAN_ID = "com.supcrm.xinlin.linkman_id";
    ArrayAdapter<Customer> adapter;
    private ArrayList<Customer> mList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("客户");
        mList = CustomerList.get(getActivity()).getCustomer();
        adapter = new ArrayAdapter<Customer>(getActivity(),android.R.layout.simple_list_item_1,mList);
        setListAdapter(adapter);

    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id){

        Customer customer=(Customer)getListAdapter().getItem(position);
        int link_man=customer.getLinkMan_id();
        Intent i=new Intent(getActivity(),CustomerItemActivity.class);
        i.putExtra(EXTRA_Cust_ID,customer.getId());
        i.putExtra(EXTRA_LINKMAN_ID,link_man);
        startActivityForResult(i,0);
    }

    @Override
    public void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
    }

}
