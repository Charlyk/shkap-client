package com.shkap.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.EditText;

import com.shkap.R;
import com.shkap.model.ViewInitializer;
import com.shkap.util.ThingInput;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddActivity extends ViewInitializer {

    @Bind(R.id.title_label) EditText mTitleLabel;
    @Bind(R.id.description_label) EditText mDescriptionLabel;
    @Bind(R.id.price_label) EditText mPriceLabel;
    private ThingInput mThingInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);
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
        mThingInput = new ThingInput.Builder().title(title).description(description)
                .price(price).build();
    }
}
