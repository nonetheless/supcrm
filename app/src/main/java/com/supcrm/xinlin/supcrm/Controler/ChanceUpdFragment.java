package com.supcrm.xinlin.supcrm.Controler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.supcrm.xinlin.supcrm.Dao.Chance;
import com.supcrm.xinlin.supcrm.Dao.ChanceList;
import com.supcrm.xinlin.supcrm.Dao.Customer;
import com.supcrm.xinlin.supcrm.Dao.CustomerList;
import com.supcrm.xinlin.supcrm.R;

/**
 * Created by xinlin on 16/6/26.
 */
public class ChanceUpdFragment extends Fragment {

    public static final String EXTRA_Chance_ID = "com.supcrm.xinlin.chance_id";
    int id;
    Chance chance;
    EditText name;
    TextView custname;
    EditText saler;
    EditText info;
    Button upd;

    public static Fragment newInstance(int contractId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_Chance_ID, contractId);
        ChanceUpdFragment fragment = new ChanceUpdFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        id = (int)getArguments().getSerializable(EXTRA_Chance_ID);
        chance = ChanceList.get(getActivity()).getOne(id);
        getActivity().setTitle("修改商机详情");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_chance_upd, container, false);
        name=(EditText) v.findViewById(R.id.contract_cname);
        custname=(TextView)v.findViewById(R.id.contract_cust);
        saler=(EditText)v.findViewById(R.id.contract_saler);
        info=(EditText)v.findViewById(R.id.contract_info);
        upd=(Button)v.findViewById(R.id.contract_add);


        name.setText(chance.getName());
        Customer one= CustomerList.get(getActivity()).getOneCustomer(chance.getCustId());
        custname.setText(one.getName());
        saler.setText(chance.getSaler());
        info.setText(chance.getInfo());
        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chance.setName(name.getText().toString().trim());
                chance.setSaler(saler.getText().toString().trim());
                chance.setInfo(info.getText().toString().trim());
                ChanceList.get(getActivity()).upd(chance);
                Intent i=new Intent();
                getTargetFragment().onActivityResult(0,1,i);
                getActivity().getSupportFragmentManager().popBackStack();

            }
        });

        return v;
    }

}
