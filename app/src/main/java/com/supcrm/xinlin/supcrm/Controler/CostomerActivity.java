package com.supcrm.xinlin.supcrm.Controler;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.supcrm.xinlin.supcrm.R;

public class CostomerActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return new CustomerListFragment();
    }


    @Override
    protected int getActivityId() {
        int id= R.layout.activity_bussiness;
        return id;
    }

    @Override
    protected void fabAction() {
        Intent i=new Intent(this,CustomerAddActivity.class);
        startActivity(i);

    }


}
