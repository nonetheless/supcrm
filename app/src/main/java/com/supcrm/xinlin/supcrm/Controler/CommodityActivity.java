package com.supcrm.xinlin.supcrm.Controler;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.supcrm.xinlin.supcrm.R;

public class CommodityActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return new CommodityListFragment();
    }

    @Override
    protected int getActivityId() {
        int id= R.layout.activity_bussiness;
        return id;
    }

    @Override
    protected void fabAction() {
        boolean isNew = true;
        Intent i = new Intent(this, CommodityAddActivity.class);
        i.putExtra(CommodityAddFragment.EXTRA_COMMODITY_ID,-1);
        i.putExtra(CommodityAddFragment.EXTRA_COMMODITY_NEW,isNew);
        startActivity(i);
    }
}
