package com.chetana.ramesh.chetana;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    CollapsingToolbarLayout collapsingToolbar;
    private int backPressedCount = 1;
    private long backpressedTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        collapsingToolbar = findViewById(R.id.collapsing_toolbar_layout);
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.toolbar_txt));


        collapsingToolbar.setTitle("CHETANA");
        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.toolbar_txt));

        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction().replace(R.id.container_Dash, new HomeFragment()).commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if (backPressedCount == 2) {
                if (((System.currentTimeMillis() - backpressedTime) / 1000) <= 5)
                    super.onBackPressed();
                else {
                    backPressedCount = 1;
                    backpressedTime = System.currentTimeMillis();
                    Toast.makeText(this, getString(R.string.press_back_again_to_exit), Toast.LENGTH_SHORT).show();
                }
            } else {
                backPressedCount++;
                backpressedTime = System.currentTimeMillis();
                Toast.makeText(this, getString(R.string.press_back_again_to_exit), Toast.LENGTH_SHORT).show();
            }

        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about) {
            Intent intent = new Intent(MainActivity.this, AboutUs.class);
            startActivity(intent);
            this.finish();


        } else if (id == R.id.nav_feedback) {
            Intent intent = new Intent(MainActivity.this, FaqActivity.class);
            startActivity(intent);
            this.finish();

        } else if (id == R.id.nav_checkforupdates) {

        } else if (id == R.id.nav_loginout) {


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
