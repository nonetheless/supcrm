package com.supcrm.xinlin.supcrm.Controler;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.supcrm.xinlin.supcrm.Dao.CustProcess;
import com.supcrm.xinlin.supcrm.Dao.Customer;
import com.supcrm.xinlin.supcrm.Dao.CustomerList;
import com.supcrm.xinlin.supcrm.R;

import java.util.ArrayList;
import java.util.Date;

import razerdp.widget.UnderLineLinearLayout;

public class CustProcessFragment extends Fragment {

    private Button addButton;
    private Button subButton;
    private UnderLineLinearLayout mUnderLineLinearLayout;
    public static final String EXTRA_Cust_ID = "com.supcrm.xinlin.customer_id";
    int id;
    ArrayList<CustProcess> processes;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        id = (int)getActivity().getIntent().getSerializableExtra(EXTRA_Cust_ID);
        processes = CustomerList.get(getActivity()).getOneCustomer(id).getProcesses();
        Customer one= CustomerList.get(getActivity()).getOneCustomer(id);
        getActivity().setTitle(one.getName());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cust_process, container, false);

        addButton = (Button) v.findViewById(R.id.add);
        subButton = (Button) v.findViewById(R.id.sub);
        mUnderLineLinearLayout = (UnderLineLinearLayout) v.findViewById(R.id.underline_layout);

        for(CustProcess temp:processes){
            mUnderLineLinearLayout.addView(addItem(temp));
        }
        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subItem();
                int num=processes.size()-1;
                CustProcess a=processes.remove(num);
                CustomerList.get(getActivity()).delProcess(id,a.getId());
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),CustProcessAddActivity.class);
                i.putExtra(EXTRA_Cust_ID,id);
                startActivityForResult(i,0);
            }
        });
        return v;
    }

    private View addItem(CustProcess c){
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.item_test, mUnderLineLinearLayout, false);
        ((TextView) v.findViewById(R.id.tx_action)).setText(c.getContent());
        Date date=c.getDate();
        int year = (2016-116)+date.getYear();
        int mouth=date.getMonth()+1;
        ((TextView) v.findViewById(R.id.tx_action_time)).setText(year+"-"+mouth+"-"+date.getDate());
        ((TextView) v.findViewById(R.id.tx_action_status)).setText(c.getStage());
        return v;
    }

    private int subItem() {
        if (mUnderLineLinearLayout.getChildCount() > 0) {
            mUnderLineLinearLayout.removeViews(mUnderLineLinearLayout.getChildCount() - 1, 1);
            return 1;
        }
        return 0;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        processes = CustomerList.get(getActivity()).getOneCustomer(id).getProcesses();
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case 1:
                while(true){
                    int i=subItem();
                    if(i==0){
                        break;
                    }
                }
                for(CustProcess temp:processes){
                    mUnderLineLinearLayout.addView(addItem(temp));
                }

            default:
                break;
        }
    }


}