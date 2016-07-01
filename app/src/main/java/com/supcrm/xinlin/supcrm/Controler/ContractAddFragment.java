package com.supcrm.xinlin.supcrm.Controler;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
public class ContractAddFragment extends Fragment {
    public static final String EXTRA_Contract_ID = "com.supcrm.xinlin.contract_id";
    public static final String EXTRA_COMMODITY_ID = "com.supcrm.xinlin.commodity_id";
    private static final String EXTRA_DATE="com.example.xinlin.date";
    public static final String EXTRA_Cust_ID = "com.supcrm.xinlin.customer_id";
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

    public ContractAddFragment() {
        // Required empty public constructor
    }



    public static Fragment newInstance(int contractId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_Contract_ID, contractId);
        ContractAddFragment fragment = new ContractAddFragment();
        fragment.setArguments(args);

        return fragment;
    }
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        cust = (int)getActivity().getIntent().getSerializableExtra(EXTRA_Cust_ID);

        getActivity().setTitle("新合同");

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
                getActivity().finish();
            }
        });


        good.setText("选择商品");
        time=new Date();

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
                dialog.setTargetFragment(ContractAddFragment.this,Request_DATA);
                dialog.show(fm,"date");

            }
        });

        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Contract a = new Contract();
                a.setCustomerId(cust);
                Customer b= CustomerList.get(getActivity()).getOneCustomer(cust);
                a.setCustomerName(b.getName());
                a.setName(name.getText().toString());
                a.setCommodityId(comId);
                a.setDate(time);
                a.setSaler(saler.getText().toString());
                a.setNum(Integer.parseInt(num.getText().toString()));
                Log.d("ggg",saler.getText().toString());
                ContractList.get(getActivity()).add(a);

                Intent i=new Intent();
                getActivity().setResult(1,i);
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
