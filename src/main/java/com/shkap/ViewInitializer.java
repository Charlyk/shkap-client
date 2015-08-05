package com.shkap;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Eduard on 02.08.2015.
 */
public abstract class ViewInitializer extends AppCompatActivity implements Toolbar.OnMenuItemClickListener,
        View.OnClickListener, SearchView.OnQueryTextListener, MenuItemCompat.OnActionExpandListener {

    protected Toolbar mToolbar;
    protected DrawerLayout mDrawerLayout;
    protected DrawerFragment mDrawerFragment;
    protected Intent mSearchIntent;
    protected SearchManager searchManager;
    protected SearchView searchView;

    protected abstract void initToolbar ();

    // Инициализация меню навигации
    protected void initNavDrawer() {
        mDrawerFragment = (DrawerFragment) getSupportFragmentManager()
                .findFragmentById(R.id.navigation_fragment);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerFragment.setUp(mDrawerLayout, mToolbar);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        return false;
    }

    @Override
    public void onClick(View v) {
    }
}
