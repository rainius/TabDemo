package com.dmtech.tabdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private TabLayout mTabs;
    private ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment with the ProductGrid theme
        View view = inflater.inflate(R.layout.home_fragment_layout, container, false);

        mTabs = view.findViewById(R.id.tabs);
        mViewPager = view.findViewById(R.id.viewpager);

        ProductStaggeredGridFragment focusFragmen = new ProductStaggeredGridFragment();

        List<Fragment> fragments = new ArrayList<>();

        fragments.add(ProductStaggeredGridFragment.asFocus());
        fragments.add(ProductStaggeredGridFragment.asDiscover());
        String [] titles = new String[] {
                getString(R.string.home_tab_title_1),
                getString(R.string.home_tab_title_2),
        };

        HomeViewPagerAdapter adapter = new HomeViewPagerAdapter(getFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);
        mTabs.setupWithViewPager(mViewPager);

        return view;
    }

    private class HomeViewPagerAdapter extends FragmentPagerAdapter {

        List<Fragment> fragments;
        String[] titles;

        public HomeViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] titles) {
            super(fm);
            this.fragments = fragmentList;
            this.titles = titles;
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

}
