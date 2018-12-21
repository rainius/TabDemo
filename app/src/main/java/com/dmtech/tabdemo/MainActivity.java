package com.dmtech.tabdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.dmtech.tabdemo.Utils.Utils;

public class
MainActivity extends AppCompatActivity {


    @Override
    protected void onResume() {
        super.onResume();

        // 增加代码判断登录状态:
        if (TextUtils.isEmpty(Utils.getUserEmail(this))) {
//            Intent intent = new Intent(this, LoginActivity.class);
//            startActivity(intent);
//            return;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_note_list, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_item_user:
                Intent intent = new Intent(this, UserInfoActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private ProductListFragment mListFragment = new ProductListFragment();
    private ProductGridFragment mGridFragment = new ProductGridFragment();
//    private ProductStaggeredGridFragment mStaggeredGridFragment = new ProductStaggeredGridFragment();
    private HomeFragment mHomeFragment = new HomeFragment();
    private Fragment currentFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_staggered:
                    switchFragment(mHomeFragment).commit();
                    return true;
                case R.id.navigation_grid:
                    switchFragment(mGridFragment).commit();
                    return true;
                case R.id.navigation_list:
                    switchFragment(mListFragment).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navigation.setSelectedItemId(R.id.navigation_staggered);
    }


    FragmentTransaction switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.container, targetFragment, targetFragment.getClass().getName());
        } else {
            transaction.hide(currentFragment).show(targetFragment);
        }
        currentFragment = targetFragment;
        return transaction;
    }

}
