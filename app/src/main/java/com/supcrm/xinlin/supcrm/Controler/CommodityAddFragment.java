package com.supcrm.xinlin.supcrm.Controler;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.supcrm.xinlin.supcrm.Dao.Commodity;
import com.supcrm.xinlin.supcrm.Dao.CommodityList;
import com.supcrm.xinlin.supcrm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommodityAddFragment extends Fragment {


    public static final String EXTRA_COMMODITY_ID = "com.supcrm.xinlin.commodity_id";
    public static final String EXTRA_COMMODITY_NEW = "com.supcrm.xinlin.commodity_new";

    EditText name;
    EditText kind;
    EditText price;
    EditText num;
    EditText info;
    CommodityList list;
    int commodityId;
    Button add;
    Button cancel;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        commodityId=(int)getActivity().getIntent().getSerializableExtra(EXTRA_COMMODITY_ID);
        list= CommodityList.get(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        commodityId = (int)getActivity().getIntent().getSerializableExtra(EXTRA_COMMODITY_ID);
        final boolean isNew =(boolean)getActivity().getIntent().getSerializableExtra(EXTRA_COMMODITY_NEW);
        View v = inflater.inflate(R.layout.fragment_commodity_add, parent, false);
        Commodity com=CommodityList.get(getActivity()).getCommodity(commodityId);

        name=(EditText)v.findViewById(R.id.commodity_name);
        kind=(EditText)v.findViewById(R.id.commodity_kind);
        price=(EditText)v.findViewById(R.id.commodity_price);
        num=(EditText) v.findViewById(R.id.commodity_num);
        info=(EditText)v.findViewById(R.id.commodity_info);
        add = (Button) v.findViewById(R.id.link_add);
        if(!isNew) {
            info.setText(com.getInfo());
            name.setText(com.getName());
            kind.setText(com.getKind());
            num.setText(Integer.toString(com.getNum()));
            price.setText(Double.toString(com.getPrice()));
        }
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(isNew){
                    list.add(name.getText().toString(), kind.getText().toString(), price.getText().toString(), num.getText().toString(),info.getText().toString());
                }
                else{
                    list.store(commodityId,name.getText().toString(), kind.getText().toString(), price.getText().toString(), num.getText().toString(),info.getText().toString());
                }
                Intent i=new Intent(getActivity(),CommodityActivity.class);
                i.putExtra(EXTRA_COMMODITY_ID,commodityId);
                startActivity(i);
                getActivity().finish();


            }
        });
        cancel=(Button)v.findViewById(R.id.link_del);
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),CommodityActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });
        return v;

    }

}
