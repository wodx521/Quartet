package com.lr.quartet;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lr.baselibrary.base.BaseFragment;

import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private FrameLayout flMain;
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();

        initData();
    }

    private void initData() {
        navigation.setItemIconTintList(null);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                String title = menuItem.getTitle().toString();
                switch (itemId) {
                    case R.id.navigation_one:
                        addFragment(0, title);
                        return true;
                    case R.id.navigation_two:
                        addFragment(1, title);
                        return true;
                    case R.id.navigation_third:
                        addFragment(2, title);
                        return true;
                    case R.id.navigation_four:
                        addFragment(3, title);
                        return true;
                    default:
                }
                return false;
            }
        });
        navigation.setSelectedItemId(R.id.navigation_one);
    }

    protected void addFragment(int position, String title) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragmentByTag = getSupportFragmentManager().findFragmentByTag(title);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            fragment.setUserVisibleHint(false);
            fragmentTransaction.hide(fragment);
        }
        if (fragmentByTag != null) {
            fragmentByTag.setUserVisibleHint(true);
            fragmentTransaction.show(fragmentByTag);
        } else {
            BaseFragment fragment = MainFragmentFactory.getFragment(position);
            fragmentTransaction.add(R.id.flMain, fragment, title);
            fragment.setUserVisibleHint(true);
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void initView() {
        flMain = findViewById(R.id.flMain);
        navigation = findViewById(R.id.navigation);
    }
}
