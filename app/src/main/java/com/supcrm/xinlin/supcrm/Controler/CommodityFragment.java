package com.supcrm.xinlin.supcrm.Controler;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.supcrm.xinlin.supcrm.Dao.Commodity;
import com.supcrm.xinlin.supcrm.Dao.CommodityList;
import com.supcrm.xinlin.supcrm.R;

public class CommodityFragment extends Fragment {

    public static final String EXTRA_COMMODITY_ID = "com.supcrm.xinlin.commodity_id";
    public static final String EXTRA_COMMODITY_NEW = "com.supcrm.xinlin.commodity_new";
    public static final String EXTRA_COMMODITY_SHOW="com.supcrm.xinlin.commodity_show";
    TextView id;
    TextView name;
    TextView kind;
    TextView price;
    TextView num;
    TextView info;
    CommodityList list;
    int commodityId;
    Button upd;
    Button del;
    Integer isShow;

    public static CommodityFragment newInstance(int Id) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_COMMODITY_ID, Id);
        CommodityFragment fragment =new CommodityFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        commodityId=(int)getActivity().getIntent().getSerializableExtra(EXTRA_COMMODITY_ID);
        list= CommodityList.get(getActivity());
        isShow=(Integer)getActivity().getIntent().getSerializableExtra(EXTRA_COMMODITY_SHOW);
        Log.d("gggCom2",String.valueOf(commodityId));
        getActivity().setTitle((CharSequence) list.getCommodity(commodityId).getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        commodityId = (int)getActivity().getIntent().getSerializableExtra(EXTRA_COMMODITY_ID);
        View v = inflater.inflate(R.layout.fragment_commodity, parent, false);
        Commodity com=CommodityList.get(getActivity()).getCommodity(commodityId);
        id=(TextView) v.findViewById(R.id.commodity_id);
        id.setText(Integer.toString(com.getId()));
        name=(TextView)v.findViewById(R.id.commodity_name);
        name.setText(com.getName());
        kind=(TextView)v.findViewById(R.id.commodity_kind);
        kind.setText(com.getKind());
        price=(TextView)v.findViewById(R.id.commodity_price);
        price.setText(Double.toString(com.getPrice()));
        num=(TextView) v.findViewById(R.id.commodity_num);
        num.setText(Integer.toString(com.getNum()));
        info=(TextView)v.findViewById(R.id.commodity_info);
        info.setText(com.getInfo());

        upd=(Button)v.findViewById(R.id.link_add);
        upd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                list.store(commodityId,name.getText().toString(), kind.getText().toString(),
                        price.getText().toString(), num.getText().toString(),info.getText().toString());
                Intent i=new Intent(getActivity(),CommodityAddActivity.class);
                i.putExtra(EXTRA_COMMODITY_ID,commodityId);
                boolean isNew=false;
                i.putExtra(EXTRA_COMMODITY_NEW,isNew);
                startActivity(i);
                getActivity().finish();


            }
        });
        del=(Button)v.findViewById(R.id.link_del);
        del.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                list.del(commodityId);
                Intent i=new Intent(getActivity(),CommodityActivity.class);
                startActivity(i);
                getActivity().finish();

            }
        });
        if(isShow!=null){
            del.setVisibility(View.INVISIBLE);
            upd.setVisibility(View.INVISIBLE);
        }

        return v;

    }

}
