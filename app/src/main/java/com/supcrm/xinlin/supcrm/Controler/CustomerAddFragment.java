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

import com.supcrm.xinlin.supcrm.Dao.CustomerList;
import com.supcrm.xinlin.supcrm.Dao.LinkMan;
import com.supcrm.xinlin.supcrm.Dao.LinkManList;
import com.supcrm.xinlin.supcrm.R;

/**
 * Created by xinlin on 16/6/26.
 */
public class CustomerAddFragment extends Fragment {
    public static final String EXTRA_LINKMAN_CUST = "com.supcrm.xinlin.linkman_cust";
    public static final String EXTRA_LINKMAN_ID = "com.supcrm.xinlin.linkman_id";
    public static final String EXTRA_LINKNEW_ID ="com.supcrm.xinlin.linknew_id";
    public static final String EXTRA_Cust_ID = "com.supcrm.xinlin.customer_id";

    EditText name;
    Button addLink;
    Button findLink;
    Button sure;
    Button cancel;
    int linkId;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        getActivity().setTitle("新客户");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_customer_add, container, false);
        name = (EditText)v.findViewById(R.id.contract_name);
        addLink=(Button)v.findViewById(R.id.link_add);
        findLink=(Button)v.findViewById(R.id.link_find);
        sure=(Button)v.findViewById(R.id.contract_add);
        cancel=(Button)v.findViewById(R.id.contract_del);

        linkId=-1;
        addLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),LinkAddActivity.class);
                Integer a=new Integer(1);
                i.putExtra(EXTRA_LINKMAN_CUST,a);
                i.putExtra(EXTRA_LINKNEW_ID,1);
                i.putExtra(EXTRA_LINKNEW_ID,true);
                startActivityForResult(i,0);

            }
        });

        findLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),LinkFindCustActivity.class);
                Integer a=new Integer(1);
                i.putExtra(EXTRA_LINKMAN_CUST,a);
                startActivityForResult(i,0);
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = CustomerList.get(getActivity()).addCust(name.getText().toString(),linkId);
                if(linkId!=-1) {
                    Intent i = new Intent(getActivity(), CustomerItemActivity.class);
                    i.putExtra(EXTRA_Cust_ID, id);
                    i.putExtra(EXTRA_LINKMAN_ID, linkId);
                    startActivity(i);
                    getActivity().finish();
                }
                else {
                    Intent i=new Intent(getActivity(),CostomerActivity.class);
                    startActivity(i);
                    getActivity().finish();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),CostomerActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });



        return v;
    }

    @Override
    public void onActivityResult(int req, int rest, Intent data) {
        if(rest==11){
            //新建联系人成功
            linkId=(int)data.getSerializableExtra(EXTRA_LINKMAN_CUST);
            LinkMan link=LinkManList.get(getActivity()).getLinkMan(linkId);
            addLink.setText(link.getName());
            findLink.setText("链接联系人");
        }
        if(rest==12){
            linkId=(int)data.getSerializableExtra(EXTRA_LINKMAN_CUST);
            LinkMan link=LinkManList.get(getActivity()).getLinkMan(linkId);
            findLink.setText(link.getName());
            addLink.setText("新建联系人");
        }
        Log.d("LinkId",String.valueOf(linkId));
    }
}
