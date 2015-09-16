package com.shkap.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.EditText;

import com.shkap.R;
import com.shkap.shkapsdk.ApiInfo;
import com.shkap.shkapsdk.ShkapClient;
import com.shkap.util.Result;
import com.shkap.util.ShkapHandler;
import com.shkap.util.ViewInitializer;
import com.shkap.util.ThingInput;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddActivity extends ViewInitializer {

    @Bind(R.id.title_label) EditText mTitleLabel;
    @Bind(R.id.description_label) EditText mDescriptionLabel;
    @Bind(R.id.price_label) EditText mPriceLabel;
    private ShkapClient<ThingInput> mClient;
    private ShkapHandler mShkapHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);
        mClient = new ShkapClient<>();
        mShkapHandler = new ShkapHandler();
    }

    @Override
    protected void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_addActivity);
        setSupportActionBar(mToolbar);
    }

    @Override
    protected void initViews() {
        initToolbar();
        initNavDrawer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }
    @OnClick(R.id.submitButton)
    public void addThing() {
        String title = mTitleLabel.getText().toString();
        String description = mDescriptionLabel.getText().toString();
        int price = Integer.parseInt(mPriceLabel.getText().toString());
        ThingInput thingInput = new ThingInput.Builder().title(title).description(description)
                .price(price).build();
        try {
            mClient.post(ApiInfo.thing("1").toString(), thingInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //// TODO: 16.09.2015 Это временно, ничего лучше пока не пришло в голову
        if (mShkapHandler.mResult.isSuccessful()) {
            String value = mShkapHandler.mResult.getValue();
        }
    }
}
