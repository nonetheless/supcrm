package com.supcrm.xinlin.supcrm.Controler;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.supcrm.xinlin.supcrm.Dao.Chance;
import com.supcrm.xinlin.supcrm.Dao.ChanceList;
import com.supcrm.xinlin.supcrm.Dao.ChanceProcess;
import com.supcrm.xinlin.supcrm.R;

import java.util.ArrayList;
import java.util.Date;

import razerdp.widget.UnderLineLinearLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChanceProcessFragment extends Fragment {

    private Button addButton;
    private Button subButton;
    private UnderLineLinearLayout mUnderLineLinearLayout;
    public static final String EXTRA_Chance_ID = "com.supcrm.xinlin.chance_id";
    int id;
    ArrayList<ChanceProcess> processes;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        id = (int)getActivity().getIntent().getSerializableExtra(EXTRA_Chance_ID);
        processes = ChanceList.get(getActivity()).getOne(id).getList();
        Chance one=ChanceList.get(getActivity()).getOne(id);
        getActivity().setTitle(one.getName());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cust_process, container, false);

        addButton = (Button) v.findViewById(R.id.add);
        subButton = (Button) v.findViewById(R.id.sub);
        mUnderLineLinearLayout = (UnderLineLinearLayout) v.findViewById(R.id.underline_layout);

        for(ChanceProcess temp:processes){
            mUnderLineLinearLayout.addView(addItem(temp));
        }
        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subItem();
                int num=processes.size()-1;
                ChanceProcess a=processes.remove(num);
                ChanceList.get(getActivity()).delProcess(id,a.getId());
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),ChanceProcessAddActivity.class);
                i.putExtra(EXTRA_Chance_ID,id);
                startActivityForResult(i,0);
            }
        });
        return v;
    }

    private View addItem(ChanceProcess c){
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.item_test, mUnderLineLinearLayout, false);
        ((TextView) v.findViewById(R.id.tx_action)).setText(c.getContent());
        Date date=c.getDate();
        int year = (2016-116)+date.getYear();
        int mouth=date.getMonth()+1;
        ((TextView) v.findViewById(R.id.tx_action_time)).setText(year+"-"+mouth+"-"+date.getDate());
        ((TextView) v.findViewById(R.id.tx_action_status)).setText(c.getName());
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
        processes = ChanceList.get(getActivity()).getOne(id).getList();
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case 1:
                while(true){
                    int i=subItem();
                    if(i==0){
                        break;
                    }
                }
                for(ChanceProcess temp:processes){
                    mUnderLineLinearLayout.addView(addItem(temp));
                }

            default:
                break;
        }
    }

}
