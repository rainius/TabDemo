package com.dmtech.tabdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.dmtech.tabdemo.Utils.Utils;
import com.dmtech.tabdemo.http.HttpHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UserInfoActivity extends AppCompatActivity {


    private ProgressBar mProgressBar;
    private TextView mEmailView;
    private TextView mPersonalSignView;
    private Button mLogoutView;

    private Handler mHandler = new Handler();

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        mProgressBar = findViewById(R.id.load_progress);
        mEmailView = findViewById(R.id.tv_email);
        mPersonalSignView = findViewById(R.id.tv_personal_sign);
        mLogoutView = findViewById(R.id.btn_logout);
        mLogoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this, LoginActivity.class);
                startActivity(intent);
                onLogout();
            }
        });

        loadUserInfo();
    }


    private void loadUserInfo() {
        String email = Utils.getUserEmail(this);

        // 当前并没有登录
        if (TextUtils.isEmpty(email)) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }


        OkHttpClient client = new OkHttpClient();

        // 创建表单，包含email和密码等数据
        FormBody.Builder fb = new FormBody.Builder();
        FormBody formBody = fb.add("email", email)
                .build();
        Request request = new Request.Builder()
                .url(HttpHelper.getUserInfoUrl())
                .post(formBody)
                .build();

        showProgress(true);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        showProgress(false);
                        Toast.makeText(UserInfoActivity.this, R.string.error_check_network, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String rsp = response.body().string();
                Log.d("ZJ", "Load user info: " + rsp);

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        showProgress(false);
                        try {
                            JSONObject jo = new JSONObject(rsp);
                            int code = jo.optInt("code", 0);
                            String email = jo.optString("email");
                            String personalSign = jo.optString("personalSign");
                            if (code == 1) {
                                mEmailView.setText(email);
                                mPersonalSignView.setText(personalSign);
                            } else {
                                finish();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void onLogout() {
        Utils.saveUserEmail(this, "");
        finish();
    }

    private void showProgress(final boolean show) {
        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}