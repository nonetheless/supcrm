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

import com.supcrm.xinlin.supcrm.Dao.LinkMan;
import com.supcrm.xinlin.supcrm.Dao.LinkManList;
import com.supcrm.xinlin.supcrm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LinkManFragment extends Fragment {

    public static final String EXTRA_LINKMAN_ID = "com.supcrm.xinlin.linkman_id";
    public static final String EXTRA_LINKNEW_ID ="com.supcrm.xinlin.linknew_id";

    private LinkMan linkMan;
    TextView name;
    TextView address;
    TextView tel;
    TextView qq;
    Button upd;
    Button del;
    int linkId;
    LinkManList list;

    public LinkManFragment() {
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
        linkId = (int)getActivity().getIntent().getSerializableExtra(EXTRA_LINKMAN_ID);
        list=LinkManList.get(getActivity());
        linkMan=list.getLinkMan(linkId);
        getActivity().setTitle(linkMan.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        linkId = (int)getActivity().getIntent().getSerializableExtra(EXTRA_LINKMAN_ID);
        linkMan= LinkManList.get(getActivity()).getLinkMan(linkId);
        View v = inflater.inflate(R.layout.fragment_link_man, parent, false);

        name=(TextView)v.findViewById(R.id.link_name);
        name.setText(linkMan.getName());
        address=(TextView)v.findViewById(R.id.link_address);
        address.setText(linkMan.getCompany());
        tel=(TextView)v.findViewById(R.id.link_tel);
        tel.setText(linkMan.getTel());
        qq=(TextView)v.findViewById(R.id.link_qq);
        qq.setText(linkMan.getQQ());

        upd=(Button)v.findViewById(R.id.link_add);
        upd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),LinkAddActivity.class);
                i.putExtra(LinkListFragment.EXTRA_LINKMAN_ID,linkId);
                boolean isNew=false;
                i.putExtra(LinkListFragment.EXTRA_LINKNEW_ID,isNew);
                startActivity(i);
                getActivity().finish();


            }
        });
        del=(Button)v.findViewById(R.id.link_del);
        del.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("SUB", "id="+String.valueOf(linkId));
                list.del(linkId);
                Intent i=new Intent(getActivity(),BussinessActivity.class);
                startActivity(i);
                getActivity().finish();

            }
        });



        return v;
    }

}
