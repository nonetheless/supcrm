package com.supcrm.xinlin.supcrm.Controler;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.supcrm.xinlin.supcrm.Dao.Commodity;
import com.supcrm.xinlin.supcrm.Dao.CommodityList;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LinkListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommodityListFragment extends ListFragment {
    private ArrayList<Commodity> mList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String EXTRA_COMMODITY_ID = "com.supcrm.xinlin.commodity_id";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayAdapter<Commodity> adapter;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LinkListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CommodityListFragment newInstance(String param1, String param2) {
        CommodityListFragment fragment = new CommodityListFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(" 商品");
        mList = CommodityList.get(getActivity()).getCommodity();
        adapter = new ArrayAdapter<Commodity>(getActivity(),android.R.layout.simple_list_item_1,mList);
        setListAdapter(adapter);

    }

    @Override
    public void onListItemClick(ListView l,View v,int position,long id){

        Commodity commodity = (Commodity) (getListAdapter()).getItem(position);
        Intent i=new Intent(getActivity(),CommodityItemActivity.class);
        i.putExtra(EXTRA_COMMODITY_ID,commodity.getId());
        startActivityForResult(i,0);
    }

    @Override
    public void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
    }


}
