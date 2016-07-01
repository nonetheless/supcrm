package com.supcrm.xinlin.supcrm.Controler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.supcrm.xinlin.supcrm.Dao.Chance;
import com.supcrm.xinlin.supcrm.Dao.ChanceList;
import com.supcrm.xinlin.supcrm.Dao.Commodity;
import com.supcrm.xinlin.supcrm.Dao.CommodityList;
import com.supcrm.xinlin.supcrm.Dao.Customer;
import com.supcrm.xinlin.supcrm.Dao.CustomerList;
import com.supcrm.xinlin.supcrm.R;

import java.util.Date;

/**
 * Created by xinlin on 16/6/26.
 */
public class ChanceAddFragment extends Fragment {
    public static final String EXTRA_Cust_ID = "com.supcrm.xinlin.customer_id";
    public static final String EXTRA_COMMODITY_ID = "com.supcrm.xinlin.commodity_id";
    private static int Request_COM=1;

    int cust;
    int comId =-1;
    Chance chance;
    EditText name;
    TextView custname;
    EditText saler;
    EditText info;
    Button upd;
    Button com;

    public static Fragment newInstance(int contractId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_Cust_ID, contractId);
        ChanceAddFragment fragment = new ChanceAddFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        cust = (int)getActivity().getIntent().getSerializableExtra(EXTRA_Cust_ID);

        getActivity().setTitle("新商机");

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_chance_add, container, false);
        name=(EditText) v.findViewById(R.id.contract_cname);
        custname=(TextView)v.findViewById(R.id.contract_cust);
        saler=(EditText)v.findViewById(R.id.contract_saler);
        info=(EditText)v.findViewById(R.id.contract_info);
        upd=(Button)v.findViewById(R.id.contract_add);
        com=(Button)v.findViewById(R.id.commodity);
        Customer one= CustomerList.get(getActivity()).getOneCustomer(cust);

        custname.setText(one.getName());
        com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),CommodityChooseActivity.class);
                startActivityForResult(i, Request_COM);
            }
        });
        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Chance a=new Chance();
                a.setId(-1);
                a.setName(name.getText().toString());
                a.setCustId(cust);
                a.setInfo(info.getText().toString());
                a.setCommodityId(comId);

                a.setSaler(saler.getText().toString());
                ChanceList.get(getActivity()).add(a);
                Intent i=new Intent(getActivity(),CustomerItemActivity.class);
                Intent intent=new Intent();
                getActivity().setResult(1,intent);
                getActivity().finish();

            }
        });


        return v;
    }

    @Override
    public void onActivityResult(int req, int rest, Intent data){


        comId = (int) data.getSerializableExtra(EXTRA_COMMODITY_ID);
        Log.d("gggCOM",String.valueOf(comId));
        Commodity temp = CommodityList.get(getActivity()).getCommodity(comId);
        com.setText(temp.getName());

    }

}
