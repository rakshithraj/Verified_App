package com.example.verified_app;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;

import FloatingButton.FloatingActionButton;
import com.example.verified_app.dao.ItemInfo;
import com.example.verified_app.Adapter.MyAdapter;

public class SampleActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    ArrayList<ItemInfo> data=new  ArrayList<ItemInfo>();
    FloatingActionButton floatingActionButton;
    private Toolbar toolbar;
    MyAdapter mAdapter;
    RecyclerView mRecyclerView;
    Drawable mIconOpenSearch,mIconCloseSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        setTitle(this.getResources().getString(R.string.inbox));
        intialize();


        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_ab_drawer);
        }
        toolbar.setBackgroundResource(R.color.light_blue);
        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.inbox, R.string.inbox);
        mDrawerLayout.setDrawerListener(drawerToggle);


        setData();


    }

    private void intialize() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        floatingActionButton=  (FloatingActionButton) findViewById(R.id.fabButton);
        floatingActionButton.setDrawableIcon(getResources().getDrawable(R.drawable.plus));
        floatingActionButton.setBackgroundColor(this.getResources().getColor(R.color.red));
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager  mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter=new MyAdapter(data,this);
        mRecyclerView.setAdapter(mAdapter);
        mIconOpenSearch = getResources()
                .getDrawable(android.R.drawable.ic_menu_search);
        mIconCloseSearch = getResources()
                .getDrawable(android.R.drawable.ic_menu_close_clear_cancel);

    }


    private void setData() {
        ItemInfo stickInfo;

        for(int i=0;i<10;i++) {
            stickInfo = new ItemInfo();
            data.add(stickInfo);

        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;



    }

    boolean mSearchOpened=false;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(Gravity.START);
                return true;
            case R.id.action_search:
                if (mSearchOpened) {
                    closeSearchBar();
                } else {
                    openSearchBar(mSearchQuery);
                }
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
    MenuItem mSearchAction;
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
         mSearchAction = menu.findItem(R.id.action_search);
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    EditText mSearchEt;
    String mSearchQuery;
    private void openSearchBar(String queryText) {

        // Set custom view on action bar.
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.search_bar);

        // Search edit text field setup.
        mSearchEt = (EditText) actionBar.getCustomView()
                .findViewById(R.id.etSearch);
        mSearchEt.addTextChangedListener(new SearchWatcher());
        mSearchEt.setText(queryText);
        mSearchEt.requestFocus();

        // Change search icon accordingly.
        mSearchAction.setIcon(mIconCloseSearch);
        mSearchOpened = true;

    }

    private void closeSearchBar() {

        // Remove custom view.
        getSupportActionBar().setDisplayShowCustomEnabled(false);

        // Change search icon accordingly.
        mSearchAction.setIcon(mIconOpenSearch);
        mSearchOpened = false;

    }

    private class SearchWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence c, int i, int i2, int i3) {

        }

        @Override
        public void onTextChanged(CharSequence c, int i, int i2, int i3) {
            mSearchQuery = mSearchEt.getText().toString();

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }

    }



}
