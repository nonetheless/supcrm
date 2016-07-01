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

import com.supcrm.xinlin.supcrm.Dao.LinkMan;
import com.supcrm.xinlin.supcrm.Dao.LinkManList;
import com.supcrm.xinlin.supcrm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LinkAddFragment extends Fragment {

    public static final String EXTRA_LINKMAN_ID = "com.supcrm.xinlin.linkman_id";
    public static final String EXTRA_LINKNEW_ID ="com.supcrm.xinlin.linknew_id";
    public static final String EXTRA_LINKMAN_CUST = "com.supcrm.xinlin.linkman_cust";

    private LinkMan linkMan;
    EditText name;
    EditText address;
    EditText tel;
    EditText qq;
    Button upd;
    Button del;
    int linkId;
    boolean isNew;
    LinkManList list;

    public LinkAddFragment() {
        // Required empty public constructor
    }

    public static LinkManFragment newInstance(int Id) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_LINKMAN_ID, Id);
        LinkManFragment fragment = new LinkManFragment();
        fragment.setArguments(args);

        return fragment;
    }
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        Integer a= (Integer) getActivity().getIntent().getSerializableExtra(EXTRA_LINKMAN_CUST);
        list=LinkManList.get(getActivity());
        if(a!=null){
            isNew =true;
            return;
        }
        linkId = (int)getActivity().getIntent().getSerializableExtra(EXTRA_LINKMAN_ID);
        isNew=(boolean)getActivity().getIntent().getSerializableExtra(EXTRA_LINKNEW_ID);
        list=LinkManList.get(getActivity());
        linkMan= LinkManList.get(getActivity()).getLinkMan(linkId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_link_add, parent, false);


        name=(EditText)v.findViewById(R.id.link_name_add);

        address=(EditText)v.findViewById(R.id.link_address_add);

        tel=(EditText)v.findViewById(R.id.link_tel_add);

        qq=(EditText)v.findViewById(R.id.link_qq_add);
        if(!isNew) {
            qq.setText(linkMan.getQQ());
            name.setText(linkMan.getName());
            tel.setText(linkMan.getTel());
            address.setText(linkMan.getCompany());
        }

        upd=(Button)v.findViewById(R.id.link_store);
        upd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Integer a= (Integer) getActivity().getIntent().getSerializableExtra(EXTRA_LINKMAN_CUST);
                if(isNew){
                    int newId=list.add(name.getText().toString(),address.getText().toString(),tel.getText().toString(),qq.getText().toString());
                    Log.d("ADD","ADD"+name.getText().toString());
                    if(a!=null){
                        Log.d("all",name.getText().toString()+address.getText().toString()+tel.getText().toString()+qq.getText().toString());
                        Intent i=new Intent();
                        i.putExtra(EXTRA_LINKMAN_CUST,newId);
                        getActivity().setResult(11,i);
                        getActivity().finish();

                    }
                    else {
                        Intent i = new Intent(getActivity(), BussinessActivity.class);
                        startActivity(i);
                        getActivity().finish();
                    }
                }
                else {
                    list.store(linkId, name.getText().toString(), address.getText().toString(), tel.getText().toString(), qq.getText().toString());
                    Intent i = new Intent(getActivity(), BussinessActivity.class);
                    startActivity(i);
                    getActivity().finish();
                }

            }
        });
        del=(Button)v.findViewById(R.id.link_cancel);
        del.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getActivity().finish();

            }
        });



        return v;
    }

}
