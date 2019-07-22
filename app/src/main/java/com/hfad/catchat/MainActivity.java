package com.hfad.catchat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle openCloseToggle = new ActionBarDrawerToggle(this, mDrawer, toolbar,
                R.string.nav_open_drawer, R.string.nav_close_drawer);
        mDrawer.addDrawerListener(openCloseToggle);
        openCloseToggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_frame, new InboxFragment())
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        Intent intent = null;

        switch (menuItem.getItemId()) {
            case R.id.nav_inbox:
                fragment = new InboxFragment();
                break;
            case R.id.nav_drafts:
                fragment = new DraftsFragment();
                break;
            case R.id.nav_sent:
                fragment = new SentItemsFragment();
                break;
            case R.id.nav_trash:
                fragment = new TrashFragment();
                break;
            case R.id.nav_help:
                intent = new Intent(this, HelpActivity.class);
                break;
            case R.id.nav_feedback:
                intent = new Intent(this, FeedbackActivity.class);
                break;
            default:
                fragment = new InboxFragment();
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment)
                    .commit();
        } else {
            startActivity(intent);
        }

        mDrawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
