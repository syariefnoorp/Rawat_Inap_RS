package com.example.syari.sqliteapp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.syari.sqliteapp.Check_In.MasukFragment;
import com.example.syari.sqliteapp.Check_Out.KeluarFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TabActivity.SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sectionsPagerAdapter = new TabActivity.SectionsPagerAdapter(getSupportFragmentManager());

        viewPager = (ViewPager)findViewById(R.id.container);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        TabActivity.SectionsPagerAdapter adapter = new TabActivity.SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MasukFragment(), "CHECK-IN");
        adapter.addFragment(new KeluarFragment(),"CHECK-OUT");
        viewPager.setAdapter(adapter);
    }
}
