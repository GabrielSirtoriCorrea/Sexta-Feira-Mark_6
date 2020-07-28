package com.gazeboindustries.friday;

import android.os.Bundle;

import com.gazeboindustries.friday.Fragments.ConfigurationsFragment;
import com.gazeboindustries.friday.Fragments.DevicesFragments.DevicesFragment;
import com.gazeboindustries.friday.Fragments.HomeFragment;
import com.gazeboindustries.friday.Fragments.InteractionsFragments.InteractionFragment;
import com.gazeboindustries.friday.Fragments.SkillsFragments.SkillsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    private FrameLayout frame;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    changeFrame(new HomeFragment());
                    return true;
                case R.id.navigation_interacoes:
                    changeFrame(new InteractionFragment());
                    return true;
                case R.id.navigation_device:
                    changeFrame(new DevicesFragment());
                    return true;
                case R.id.navigation_skills:
                    changeFrame(new SkillsFragment());
                    return true;
                case R.id.navigation_configs:
                    changeFrame(new ConfigurationsFragment());
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeFrame(new HomeFragment());
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(R.id.navigation_home);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }


    public void changeFrame(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();

    }

}
