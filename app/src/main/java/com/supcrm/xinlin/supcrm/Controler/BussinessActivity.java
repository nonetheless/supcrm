package com.supcrm.xinlin.supcrm.Controler;


import android.content.Intent;
import android.support.v4.app.Fragment;

import com.supcrm.xinlin.supcrm.R;

public class BussinessActivity extends SingleFragmentActivity{



    @Override
    protected Fragment createFragment() {
        return new LinkListFragment();
    }


    @Override
    protected int getActivityId() {
        int id= R.layout.activity_bussiness;
        return id;
    }

    @Override
    protected void fabAction() {
        boolean isNew = true;
        Intent i = new Intent(this,LinkAddActivity.class);
        i.putExtra(LinkAddFragment.EXTRA_LINKMAN_ID,-1);
        i.putExtra(LinkAddFragment.EXTRA_LINKNEW_ID,isNew);
        startActivity(i);

    }


}