package com.supcrm.xinlin.supcrm.Controler;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v7.app.AppCompatActivity;

import com.supcrm.xinlin.supcrm.R;

public class LinkManActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("联系人");

        setContentView(R.layout.activity_fragment);
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);

        if (fragment == null) {
            fragment = new LinkManFragment();
            manager.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }

    }
}
