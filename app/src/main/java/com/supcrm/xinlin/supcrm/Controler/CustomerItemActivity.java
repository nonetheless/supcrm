package com.supcrm.xinlin.supcrm.Controler;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.supcrm.xinlin.supcrm.Dao.Customer;
import com.supcrm.xinlin.supcrm.Dao.CustomerList;
import com.supcrm.xinlin.supcrm.R;

public class CustomerItemActivity extends AppCompatActivity {
    public static final String EXTRA_Cust_ID = "com.supcrm.xinlin.customer_id";
    public static final String EXTRA_LINKMAN_ID = "com.supcrm.xinlin.linkman_id";
    int linkId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_item);

        linkId = (int)this.getIntent().getSerializableExtra(EXTRA_Cust_ID);
        Customer customer= CustomerList.get(this).getOneCustomer(linkId);
        int tempid=customer.getLinkMan_id();
        getIntent().putExtra(EXTRA_LINKMAN_ID,tempid);
        this.setTitle(customer.getName());
        ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.Tab tabA = bar.newTab().setText("跟进记录");

        ActionBar.Tab tabB = bar.newTab().setText("联系人");
        ActionBar.Tab tabC = bar.newTab().setText("商机");
        ActionBar.Tab tabD = bar.newTab().setText("合同");
       // ActionBar.Tab tabE = bar.newTab().setText("新合同");

        Fragment fragmentA = new CustProcessFragment();
        Fragment fragmentB = new LinkManFragment();
        Fragment fragmentC = new CustChanceFragment();
        Fragment fragmentD = new ContractListFragment();
        //Fragment fragmentE = ContractAddFragment.newInstance(-1);

        tabA.setTabListener(new MyTabsListener(fragmentA, this));
        tabB.setTabListener(new MyTabsListener(fragmentB,this));
        tabC.setTabListener(new MyTabsListener(fragmentC,this));
        tabD.setTabListener(new MyTabsListener(fragmentD,this));
        //tabE.setTabListener(new MyTabsListener(fragmentE,this));
        bar.addTab(tabA);
        bar.addTab(tabB);
        bar.addTab(tabC);
        bar.addTab(tabD);
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
