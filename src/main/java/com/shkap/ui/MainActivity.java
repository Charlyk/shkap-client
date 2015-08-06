package com.shkap.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.shkap.R;
import com.shkap.social.VKManager;
import com.shkap.model.ViewInitializer;

public class MainActivity extends ViewInitializer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initViews();
        VKManager vkManager = new VKManager(this, MainActivity.this);
        if (!vkManager.isLoggedIn()) {
            VKManager.logInWithVK();
        }
        VKManager.setAccesToken();
        Log.i("TAG", VKManager.getAccesToken());
    }

    @Override
    protected void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_mainActivity);
        setSupportActionBar(mToolbar);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initViews() {
        initToolbar();
        initNavDrawer();
    }
}
