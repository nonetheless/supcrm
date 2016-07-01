package com.supcrm.xinlin.supcrm.Controler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.supcrm.xinlin.supcrm.Dao.LinkMan;
import com.supcrm.xinlin.supcrm.Dao.LinkManList;
import com.supcrm.xinlin.supcrm.R;

import java.util.ArrayList;

/**
 * Created by xinlin on 16/6/26.
 */
public class LinkFindCustFragment extends ListFragment {
    private ArrayList<LinkMan> mListMans;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String EXTRA_LINKMAN_ID = "com.supcrm.xinlin.linkman_id";
    public static final String EXTRA_LINKNEW_ID ="com.supcrm.xinlin.linknew_id";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayAdapter<LinkMan> adapter;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LinkListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LinkListFragment newInstance(String param1, String param2) {
        LinkListFragment fragment = new LinkListFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.linkList_title);
        mListMans = LinkManList.get(getActivity()).getLinkMans();
        adapter = new ArrayAdapter<LinkMan>(getActivity(),android.R.layout.simple_list_item_1,mListMans);
        setListAdapter(adapter);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){

        LinkMan linkMan = (LinkMan)(getListAdapter()).getItem(position);
        getActivity().getIntent().putExtra(EXTRA_LINKMAN_ID,linkMan.getId());
        Fragment newFragment = LinkFindCustItemFragment.newInstance(linkMan.getId());

        FragmentTransaction transaction =getFragmentManager().beginTransaction();


        transaction.replace(R.id.fragmentContainer,newFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    @Override
    public void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
    }

}
