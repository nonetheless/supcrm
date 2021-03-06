package com.supcrm.xinlin.supcrm.Controler;


import android.app.Activity;
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

import com.supcrm.xinlin.supcrm.Dao.CustProcess;
import com.supcrm.xinlin.supcrm.Dao.CustomerList;
import com.supcrm.xinlin.supcrm.R;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustProcessAddFragment extends Fragment {


    public static final String EXTRA_Cust_ID = "com.supcrm.xinlin.customer_id";
    private static final String EXTRA_DATE="com.example.xinlin.date";
    int id;
    EditText stage;
    EditText content;
    Button date;
    private static int Request_DATA=0;
    Date time;


    public static CustProcessAddFragment newInstance(int id){
        Bundle args =new Bundle();
        args.putSerializable(EXTRA_Cust_ID,id);
        CustProcessAddFragment fragment=new CustProcessAddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        id = (int)getActivity().getIntent().getSerializableExtra(EXTRA_Cust_ID);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cust_process_add, container, false);
        stage =(EditText)v.findViewById(R.id.process_stage);
        content=(EditText)v.findViewById(R.id.process_info);
        date=(Button)v.findViewById(R.id.process_date);
        time=new Date();
        date.setText(time.toString());
        date.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                FragmentManager fm=getActivity().getSupportFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(time);
                dialog.setTargetFragment(CustProcessAddFragment.this,Request_DATA);
                dialog.show(fm,"date");

            }
        });
        Button store = (Button)v.findViewById(R.id.process_store);
        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustProcess temp = new CustProcess();
                temp.setDate(time);
                temp.setStage(stage.getText().toString());
                temp.setContent(content.getText().toString());
                int i=CustomerList.get(getActivity()).store(id,temp);
                Intent intent=new Intent();
                getActivity().setResult(i, intent); //intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
                getActivity().finish();
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int req, int rest, Intent data){
        if(rest!= Activity.RESULT_OK) return;
        if(req==Request_DATA){
            Date d=(Date)data.getSerializableExtra(EXTRA_DATE);
            date.setText(d.toString());
            date.refreshDrawableState();
            time=d;
        }

    }

}
