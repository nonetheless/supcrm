package com.supcrm.xinlin.supcrm.Controler;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.supcrm.xinlin.supcrm.Dao.Commodity;
import com.supcrm.xinlin.supcrm.Dao.CommodityList;
import com.supcrm.xinlin.supcrm.Dao.Contract;
import com.supcrm.xinlin.supcrm.Dao.ContractList;
import com.supcrm.xinlin.supcrm.Dao.Customer;
import com.supcrm.xinlin.supcrm.Dao.CustomerList;
import com.supcrm.xinlin.supcrm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContractFragment extends Fragment {
    public static final String EXTRA_Contract_ID = "com.supcrm.xinlin.contract_id";
    public static final String EXTRA_Cust_ID = "com.supcrm.xinlin.customer_id";
    public static final String EXTRA_LINKMAN_ID = "com.supcrm.xinlin.linkman_id";

    int id;
    private Contract contract;
    TextView cust;
    TextView saler;
    TextView time;
    TextView cname;
    TextView ckind;
    TextView cprice;
    TextView cnum;
    Button upd;
    Button del;
    int custId;

    public ContractFragment() {
        // Required empty public constructor
    }

    public static ContractFragment newInstance(int Id) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_Contract_ID, Id);
        ContractFragment fragment = new ContractFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        id = (int)getArguments().getSerializable(EXTRA_Contract_ID);
        custId=(int) getActivity().getIntent().getSerializableExtra(EXTRA_Cust_ID);
        contract = ContractList.get(getActivity()).getOnContract(id);
        getActivity().setTitle(contract.getName());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_contract, container, false);
        cust = (TextView)v.findViewById(R.id.contract_cust);
        cust.setText(contract.getCustomerName());

        saler=(TextView)v.findViewById(R.id.contract_saler);
        saler.setText(contract.getSaler());

        time=(TextView)v.findViewById(R.id.contract_date);
        time.setText(contract.getDate().toString());

        cname=(TextView)v.findViewById(R.id.contract_cname);
        Commodity commodity = CommodityList.get(getActivity()).getCommodity(contract.getCommodityId());
        cname.setText(commodity.getName());

        ckind=(TextView)v.findViewById(R.id.contract_ckind);
        ckind.setText(commodity.getKind());

        cprice=(TextView)v.findViewById(R.id.contract_cprice);
        cprice.setText(String.valueOf(commodity.getPrice()));

        cnum=(TextView)v.findViewById(R.id.contract_cnum);
        cnum.setText(String.valueOf(contract.getNum()));

        upd=(Button)v.findViewById(R.id.contract_add);
        del=(Button)v.findViewById(R.id.contract_del);

        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = ContractUpdFragment.newInstance(contract.getContractId());

                FragmentTransaction transaction =getFragmentManager().beginTransaction();
                newFragment.setTargetFragment(ContractFragment.this,0);
                //dialog.setTargetFragment(CustProcessAddFragment.this,Request_DATA);
                //dialog.show(fm,"date");

                transaction.replace(R.id.fragment_container,newFragment);
                transaction.addToBackStack(null);

                transaction.commit();

            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContractList.get(getActivity()).delOne(id);
                Customer one= CustomerList.get(getActivity()).getOneCustomer(custId);
                int link=one.getLinkMan_id();
                Intent i=new Intent(getActivity(),CustomerItemActivity.class);
                i.putExtra(EXTRA_Cust_ID,custId);
                i.putExtra(EXTRA_LINKMAN_ID,link);
                startActivity(i);
                getActivity().finish();

            }
        });
        return v;
    }



}
