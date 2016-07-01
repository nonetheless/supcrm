package com.supcrm.xinlin.supcrm.Controler;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.supcrm.xinlin.supcrm.Dao.Chance;
import com.supcrm.xinlin.supcrm.Dao.ChanceList;
import com.supcrm.xinlin.supcrm.Dao.Customer;
import com.supcrm.xinlin.supcrm.Dao.CustomerList;
import com.supcrm.xinlin.supcrm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChanceDetailFragment extends Fragment {

    int id;
    Chance chance;
    TextView name;
    TextView custname;
    TextView saler;
    TextView info;
    Button upd;

    public static final String EXTRA_Chance_ID = "com.supcrm.xinlin.chance_id";
    public ChanceDetailFragment() {
        // Required empty public constructor
    }


    public static ChanceDetailFragment newInstance(int Id) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_Chance_ID, Id);
        ChanceDetailFragment fragment = new ChanceDetailFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        id = (int)getArguments().getSerializable(EXTRA_Chance_ID);
        chance = ChanceList.get(getActivity()).getOne(id);
        getActivity().setTitle(chance.getName());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_chance_detail, container, false);
        name=(TextView) v.findViewById(R.id.contract_cname);
        custname=(TextView)v.findViewById(R.id.contract_cust);
        saler=(TextView)v.findViewById(R.id.contract_saler);
        info=(TextView)v.findViewById(R.id.contract_info);
        upd=(Button)v.findViewById(R.id.contract_add);


        name.setText(chance.getName());
        Customer one= CustomerList.get(getActivity()).getOneCustomer(chance.getCustId());
        custname.setText(one.getName());
        saler.setText(chance.getSaler());
        info.setText(chance.getInfo());

        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = ChanceUpdFragment.newInstance(chance.getId());

                FragmentTransaction transaction =getFragmentManager().beginTransaction();
                newFragment.setTargetFragment(ChanceDetailFragment.this,0);
                //dialog.setTargetFragment(CustProcessAddFragment.this,Request_DATA);
                //dialog.show(fm,"date");

                transaction.replace(R.id.fragment_container,newFragment);
                transaction.addToBackStack(null);

                transaction.commit();

            }
        });

        return v;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        id = (int)getArguments().getSerializable(EXTRA_Chance_ID);
        chance = ChanceList.get(getActivity()).getOne(id);
        name.setText(chance.getName());
        Customer one= CustomerList.get(getActivity()).getOneCustomer(chance.getCustId());
        custname.setText(one.getName());
        saler.setText(chance.getSaler());
        info.setText(chance.getInfo());
    }


}
