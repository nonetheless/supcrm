package com.supcrm.xinlin.supcrm.Controler;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.supcrm.xinlin.supcrm.Dao.Commodity;
import com.supcrm.xinlin.supcrm.Dao.CommodityList;
import com.supcrm.xinlin.supcrm.Dao.Contract;
import com.supcrm.xinlin.supcrm.Dao.ContractList;
import com.supcrm.xinlin.supcrm.Dao.Customer;
import com.supcrm.xinlin.supcrm.Dao.CustomerList;
import com.supcrm.xinlin.supcrm.R;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContractUpdFragment extends Fragment {
    public static final String EXTRA_Contract_ID = "com.supcrm.xinlin.contract_id";
    public static final String EXTRA_COMMODITY_ID = "com.supcrm.xinlin.commodity_id";
    private static final String EXTRA_DATE="com.example.xinlin.date";
    public static final String EXTRA_Cust_ID = "com.supcrm.xinlin.customer_id";
    public static final String EXTRA_LINKMAN_ID = "com.supcrm.xinlin.linkman_id";
    int id;
    Contract contract;
    EditText saler;
    Button date;
    Button good;
    EditText num;
    EditText name;
    Date time;
    Button store;
    Button cacel;
    private static int Request_DATA=0;
    private static int Request_COM=1;
    int cust;
    int comId;

    public ContractUpdFragment() {
        // Required empty public constructor
    }



    public static Fragment newInstance(int contractId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_Contract_ID, contractId);
        ContractUpdFragment fragment = new ContractUpdFragment();
        fragment.setArguments(args);

        return fragment;
    }
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        cust = (int)getActivity().getIntent().getSerializableExtra(EXTRA_Cust_ID);
        id = (int)getArguments().getSerializable(EXTRA_Contract_ID);

        contract = ContractList.get(getActivity()).getOnContract(id);
        getActivity().setTitle(contract.getName());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_contract_add, container, false);

        name=(EditText)v.findViewById(R.id.contract_name);
        saler=(EditText) v.findViewById(R.id.contract_sname);
        date=(Button) v.findViewById(R.id.contract_date1);
        good=(Button) v.findViewById(R.id.contract_com);
        num=(EditText)v.findViewById(R.id.contract_cnumber);
        cacel=(Button)v.findViewById(R.id.contract_del);
        store=(Button)v.findViewById(R.id.contract_add);
        cacel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),CustomerItemActivity.class);
                Customer one= CustomerList.get(getActivity()).getOneCustomer(cust);
                int link=one.getLinkMan_id();
                i.putExtra(EXTRA_Cust_ID,cust);
                i.putExtra(EXTRA_LINKMAN_ID,link);
                startActivity(i);
                getActivity().finish();
            }
        });


        comId = contract.getCommodityId();
        Commodity temp = CommodityList.get(getActivity()).getCommodity(comId);
        good.setText(temp.getName());
        saler.setText(contract.getSaler());
        num.setText(String.valueOf(contract.getNum()));
        time=contract.getDate();
        name.setText(contract.getName());

        good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),CommodityChooseActivity.class);
                startActivityForResult(i, Request_COM);
            }
        });

        date.setText(time.toString());
        date.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                FragmentManager fm=getActivity().getSupportFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(time);
                dialog.setTargetFragment(ContractUpdFragment.this,Request_DATA);
                dialog.show(fm,"date");

            }
        });

        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Contract a = new Contract();
                a.setCustomerId(contract.getCustomerId());
                a.setCustomerName(contract.getCustomerName());
                a.setContractId(contract.getContractId());
                a.setName(name.getText().toString());
                a.setCommodityId(comId);
                a.setDate(time);
                a.setSaler(saler.getText().toString());
                a.setNum(Integer.parseInt(num.getText().toString()));
                ContractList.get(getActivity()).upd(a);

                Customer one= CustomerList.get(getActivity()).getOneCustomer(cust);
                int link=one.getLinkMan_id();
                Intent i=new Intent(getActivity(),CustomerItemActivity.class);
                i.putExtra(EXTRA_Cust_ID,cust);
                i.putExtra(EXTRA_Cust_ID,cust);
                i.putExtra(EXTRA_LINKMAN_ID,link);
                startActivity(i);
                getActivity().finish();
            }
        });

        return v;
    }



    @Override
    public void onActivityResult(int req, int rest, Intent data){

        if(req==Request_DATA){
            Date d=(Date)data.getSerializableExtra(EXTRA_DATE);
            date.setText(d.toString());
            date.refreshDrawableState();
            time=d;
        }
        if(rest==22) {
            comId = (int) data.getSerializableExtra(EXTRA_COMMODITY_ID);
            Commodity temp = CommodityList.get(getActivity()).getCommodity(comId);
            good.setText(temp.getName());
        }


    }


}
