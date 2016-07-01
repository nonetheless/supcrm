package com.supcrm.xinlin.supcrm.Controler;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.supcrm.xinlin.supcrm.Dao.Chance;
import com.supcrm.xinlin.supcrm.Dao.ChanceList;
import com.supcrm.xinlin.supcrm.Dao.Customer;
import com.supcrm.xinlin.supcrm.Dao.CustomerList;
import com.supcrm.xinlin.supcrm.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustChanceFragment extends ListFragment {
    public static final String EXTRA_Chance_ID = "com.supcrm.xinlin.chance_id";
    public static final String EXTRA_Cust_ID = "com.supcrm.xinlin.customer_id";

    private ArrayList<Chance> chances;
    private ArrayAdapter<Chance> adapter;
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
                Intent i=new Intent(getActivity(),ChanceAddActivity.class);
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
        Customer one= CustomerList.get(getActivity()).getOneCustomer(custid);
        getActivity().setTitle(one.getName());
        setHasOptionsMenu(true);
        custid = (int)getActivity().getIntent().getSerializableExtra(EXTRA_Cust_ID);
        chances= ChanceList.get(getActivity()).getByCust(custid);
        adapter=new ArrayAdapter<Chance>(getActivity(),android.R.layout.simple_list_item_1,chances);
        setListAdapter(adapter);

    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id){

        Chance c = (Chance) (getListAdapter()).getItem(position);
        Intent i=new Intent(getActivity(),ChanceItemActivity.class);
        i.putExtra(EXTRA_Chance_ID,c.getId());
        startActivityForResult(i,1);
    }


    @Override
    public void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int req, int rest, Intent data){

        FragmentTransaction fmt = getActivity().getSupportFragmentManager().beginTransaction();
        FragmentManager fm=getActivity().getSupportFragmentManager();
        Fragment back=fm.findFragmentById(R.id.fragment_container);
        fmt.remove(back);
        Fragment t=new CustChanceFragment();
        fmt.add(R.id.fragment_container, t);
        fmt.commit();
    }

}
