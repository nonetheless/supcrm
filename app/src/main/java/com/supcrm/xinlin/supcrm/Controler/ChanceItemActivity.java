package com.supcrm.xinlin.supcrm.Controler;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.supcrm.xinlin.supcrm.Dao.Chance;
import com.supcrm.xinlin.supcrm.Dao.ChanceList;
import com.supcrm.xinlin.supcrm.Dao.Customer;
import com.supcrm.xinlin.supcrm.Dao.CustomerList;
import com.supcrm.xinlin.supcrm.R;

public class ChanceItemActivity extends AppCompatActivity {

    public static final String EXTRA_Chance_ID = "com.supcrm.xinlin.chance_id";
    public static final String EXTRA_COMMODITY_ID = "com.supcrm.xinlin.commodity_id";
    public static final String EXTRA_COMMODITY_SHOW="com.supcrm.xinlin.commodity_show";
    public static final String EXTRA_Cust_ID = "com.supcrm.xinlin.customer_id";
    Chance chance;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.del, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.item_del) {
            int i = (int)getIntent().getSerializableExtra(EXTRA_Chance_ID);
            Chance chance=ChanceList.get(this).getOne(i);
            Customer customer = CustomerList.get(this).getOneCustomer(chance.getCustId());
            ChanceList.get(this).delChance(i);
            Intent intent=new Intent();
            setResult(11,intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_item);

        int id = (int)getIntent().getSerializableExtra(EXTRA_Chance_ID);
        chance = ChanceList.get(this).getOne(id);
        setTitle(chance.getName());
        ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.Tab tabA = bar.newTab().setText("跟进记录");

        ActionBar.Tab tabB = bar.newTab().setText("商机详情");
        ActionBar.Tab tabC = bar.newTab().setText("商品信息");
        // ActionBar.Tab tabE = bar.newTab().setText("新合同");

        getIntent().putExtra(EXTRA_COMMODITY_ID,chance.getId());
        Integer m=new Integer(1);
        getIntent().putExtra(EXTRA_COMMODITY_SHOW,m);
        getIntent().putExtra(EXTRA_COMMODITY_ID,chance.getCommodityId());
        Fragment fragmentA = new ChanceProcessFragment();
        Fragment fragmentB = ChanceDetailFragment.newInstance(id);
        Fragment fragmentC = CommodityFragment.newInstance(chance.getCommodityId());
        //Fragment fragmentE = ContractAddFragment.newInstance(-1);

        tabA.setTabListener(new MyTabsListener(fragmentA, this));
        tabB.setTabListener(new MyTabsListener(fragmentB,this));
        tabC.setTabListener(new MyTabsListener(fragmentC,this));
        //tabE.setTabListener(new MyTabsListener(fragmentE,this));
        bar.addTab(tabA);
        bar.addTab(tabB);
        bar.addTab(tabC);

        // bar.addTab(tabE);
    }
    protected class MyTabsListener implements ActionBar.TabListener {

        private Fragment fragment;
        private AppCompatActivity mActivity;
        FragmentTransaction fmt;

        public MyTabsListener(Fragment fragment,AppCompatActivity activity) {
            this.fragment = fragment;
            this.mActivity = activity;

        }
        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

            FragmentTransaction fmt = mActivity.getSupportFragmentManager().beginTransaction();
            fmt.add(R.id.fragment_container, fragment);
            fmt.commit();
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            FragmentTransaction fmt = mActivity.getSupportFragmentManager().beginTransaction();
            FragmentManager fm=getSupportFragmentManager();
            Fragment back=fm.findFragmentById(R.id.fragment_container);
            if(!fragment.equals(back)){
                fmt.remove(back);
            }
            fmt.remove(fragment);
            fmt.commit();
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }
    }
}
