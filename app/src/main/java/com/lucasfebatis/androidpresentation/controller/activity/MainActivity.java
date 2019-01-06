package com.lucasfebatis.androidpresentation.controller.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lucasfebatis.androidpresentation.R;
import com.lucasfebatis.androidpresentation.controller.adapter.AbasAdapter;
import com.lucasfebatis.androidpresentation.controller.fragment.RealmFragment;
import com.lucasfebatis.androidpresentation.controller.fragment.SQLiteFragment;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);

        setupTabs();

    }


    public void setupTabs() {
        AbasAdapter adapter = new AbasAdapter( getSupportFragmentManager() );
        adapter.adicionar( new SQLiteFragment() , "SQLite Database");
        adapter.adicionar( new RealmFragment(), "Realm Database");

        ViewPager viewPager = (ViewPager) findViewById(R.id.abas_view_pager);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.abas);
        tabLayout.setupWithViewPager(viewPager);
    }

}
