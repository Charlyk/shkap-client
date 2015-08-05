package com.shkap;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vk.sdk.VKSdk;
import com.vk.sdk.VKUIHelper;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends ViewInitializer {

    private TextView mScreenName;
    private VKLoginHelper loginHelper;
    private UserData mUserData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initMainElements();
        if (loginHelper.isLoggedIn()) {
            loginHelper.grabUserData(mUserData);
        } else {
            loginHelper.logInWithVK();
            loginHelper.grabUserData(mUserData);
        }
        mScreenName.setText(mUserData.getFirstName() + " " + mUserData.getLastName());
    }

    @Override
    protected void initToolbar() {
        initNavDrawer();
        mToolbar = (Toolbar) findViewById(R.id.toolbar_mainActivity);
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.main_search_action);
        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(new ComponentName("com.shkap",
                "com.shkap.SearchableActivity")));
        searchView.setIconifiedByDefault(true);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initMainElements() {
        loginHelper = new VKLoginHelper(MainActivity.this, MainActivity.this);
        mSearchIntent = new Intent(this, SearchableActivity.class);
        mScreenName = (TextView) findViewById(R.id.social_name);
        mUserData = new UserData();
        initToolbar();
    }
}
