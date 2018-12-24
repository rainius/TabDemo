package com.dmtech.tabdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.dmtech.tabdemo.network.ProductEntry;

public class ProductListFragment extends Fragment implements ActionMenuView.OnMenuItemClickListener {



    private Handler handler = new Handler();
    private View mWaitingView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    private ActionMenuView mMenuView;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment with the ProductGrid theme
        View view = inflater.inflate(R.layout.activity_user, container, false);

        mMenuView = view.findViewById(R.id.menu_view);
        mMenuView.setOnMenuItemClickListener(this);
        getActivity().getMenuInflater().inflate(R.menu.menu_note_list, mMenuView.getMenu());
        return view;
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_user:
                Toast.makeText(getContext(), "User Info", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }
}
