package com.dmtech.tabdemo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Content extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        // 此处读取传过来的产品id，根据这个id确定你展示哪个产品的信息，并且根据这个id去查询产品详细信息
        Intent intent = getIntent();
        int id = intent.getIntExtra("productId", 0);

    }


}
