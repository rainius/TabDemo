package com.dmtech.tabdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmtech.tabdemo.network.ProductEntry;

public class ProductStaggeredGridFragment extends Fragment {

    private static final String TYPE = "type";
    private static final int TYPE_FOCUS = 0;
    private static final int TYPE_DISCOVER = 1;

    private Handler handler = new Handler();
    private View mWaitingView;

    private int type;


    public static ProductStaggeredGridFragment asFocus() {
        ProductStaggeredGridFragment fragment = new ProductStaggeredGridFragment();
        Bundle args = new Bundle();
        args.putInt(TYPE, TYPE_FOCUS);
        fragment.setArguments(args);
        return fragment;
    }

    public static ProductStaggeredGridFragment asDiscover() {
        ProductStaggeredGridFragment fragment = new ProductStaggeredGridFragment();
        Bundle args = new Bundle();
        args.putInt(TYPE, TYPE_DISCOVER);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    private void onReadNote() {
        // 启动阅读页面
        Intent intent = new Intent(getActivity(), Content.class);
        startActivity(intent);
    }



    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment with the ProductGrid theme
        View view = inflater.inflate(R.layout.product_grid_fragment, container, false);

        // Set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL));

        Bundle args = getArguments();
        int type = args.getInt(TYPE);
        int id;
        if (type == TYPE_FOCUS) {
            id = R.raw.products;
        } else {
            id = R.raw.products_discover;
        }
        ProductCardRecyclerViewAdapter adapter = new ProductCardRecyclerViewAdapter(
                ProductEntry.initProductEntryList(id, getResources()));
        recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small);
        recyclerView.addItemDecoration(new ProductGridItemDecoration(smallPadding, smallPadding));

        return view;
    }

}