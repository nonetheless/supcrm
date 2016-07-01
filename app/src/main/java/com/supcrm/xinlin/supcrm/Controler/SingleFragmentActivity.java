package com.supcrm.xinlin.supcrm.Controler;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.supcrm.xinlin.supcrm.Dao.UserList;
import com.supcrm.xinlin.supcrm.Dao.Users;
import com.supcrm.xinlin.supcrm.R;

public abstract class SingleFragmentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected abstract Fragment createFragment();
    protected abstract int getActivityId();
    protected abstract void fabAction();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityId());
        initView(savedInstanceState);
    }


    protected void initView(Bundle savedInstanceState){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(getSupportActionBar()==null) {
            setSupportActionBar(toolbar);
        }
        FragmentManager fm=getSupportFragmentManager();
        Fragment fragment=fm.findFragmentById(R.id.fragment_container);

        if(null==fragment){
            //add New Fragment
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragment_container,fragment).commit();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabAction();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Users u=UserList.get().getUser();
        final TextView name = (TextView) navigationView.getHeaderView(0).findViewById(R.id.name);
        final TextView id = (TextView) navigationView.getHeaderView(0).findViewById(R.id.staffId);
        if(u!=null) {
            name.setText(u.getName());
            id.setText(Integer.toString(u.getStaffid()));
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bussiness, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_linkman) {
            Intent i=new Intent(this,BussinessActivity.class);
            startActivity(i);
            finish();

        } else if (id == R.id.nav_customer) {
            Intent i=new Intent(this,CostomerActivity.class);
            startActivity(i);
            finish();

        } else if (id == R.id.nav_commodity) {
            Intent i=new Intent(this, CommodityActivity.class);
            startActivity(i);
            finish();

        }  else if (id == R.id.nav_share) {
            Intent i=new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
