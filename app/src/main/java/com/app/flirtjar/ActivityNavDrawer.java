package com.app.flirtjar;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.AccessToken;

import butterknife.BindView;
import butterknife.ButterKnife;
import fragments.FragmentChat;
import fragments.FragmentJar;
import fragments.FragmentMap;

public class ActivityNavDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener
{

    FragmentManager fragmentManager;

    @BindView(R.id.tv_navLogoText)
    TextView tvNavLogoText;

    @BindView(R.id.btn_nearby)
    ImageButton btnNearby;

    @BindView(R.id.btn_jar)
    ImageButton btnJar;

    @BindView(R.id.btn_chat)
    ImageButton btnChat;

    @BindView(R.id.ll_nearby)
    LinearLayout llNearby;

    @BindView(R.id.ll_jar)
    LinearLayout llJar;

    @BindView(R.id.ll_chat)
    LinearLayout llChat;

    int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);

        ButterKnife.bind(this);

        final Typeface pacifico = Typeface.createFromAsset(getAssets(), "font/Pacifico-Regular.ttf");

        tvNavLogoText.setTypeface(pacifico);

        getFacebookAuthToken();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("");

        fragmentManager = getSupportFragmentManager();

        setupBottomBar(1);

        btnJar.setSelected(true);

        llNearby.setOnClickListener(this);
        llJar.setOnClickListener(this);
        llChat.setOnClickListener(this);


        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        /*fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.getHeaderView(0).findViewById(R.id.ib_settings)
                .setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        startActivity(new Intent(ActivityNavDrawer.this, ActivitySetting.class));
                    }
                });

        toggle.setDrawerIndicatorEnabled(false);

        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_jar_active,
                getTheme());

        toggle.setHomeAsUpIndicator(drawable);

        toggle.setToolbarNavigationClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (drawer.isDrawerVisible(GravityCompat.START))
                {
                    drawer.closeDrawer(GravityCompat.START);
                } else
                {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        fragmentManager.beginTransaction()
                .replace(R.id.fl_fragmentContainer, FragmentJar.newInstance())
                .commitAllowingStateLoss();

    }

    private void getFacebookAuthToken()
    {
        if (AccessToken.getCurrentAccessToken() == null)
        {
            Intent i = new Intent(this, ActivityLogin.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }
    }

    private void setupBottomBar(int position)
    {
        switch (position)
        {
            case 0:
                if (currentPage != position)
                {
                    currentPage = position;
                    fragmentManager.beginTransaction()
                            .replace(R.id.fl_fragmentContainer, new FragmentMap())
                            .commitAllowingStateLoss();
                }
                return;

            case 1:
                if (currentPage != position)
                {
                    currentPage = position;
                    fragmentManager.beginTransaction()
                            .replace(R.id.fl_fragmentContainer, FragmentJar.newInstance())
                            .commitAllowingStateLoss();
                }
                return;

            case 2:
                if (currentPage != position)
                {
                    currentPage = position;
                    fragmentManager.beginTransaction()
                            .replace(R.id.fl_fragmentContainer, FragmentChat.newInstance())
                            .commitAllowingStateLoss();
                    return;
                }

            default:
                return;
        }
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_nav_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera)
        {
            // Handle the camera action
        } else if (id == R.id.nav_gallery)
        {

        } else if (id == R.id.nav_slideshow)
        {

        } else if (id == R.id.nav_manage)
        {

        } else if (id == R.id.nav_share)
        {

        } else if (id == R.id.nav_send)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.ll_nearby:
                setupBottomBar(0);
                btnNearby.setSelected(true);
                btnJar.setSelected(false);
                btnChat.setSelected(false);
                break;

            case R.id.ll_jar:
                setupBottomBar(1);
                btnNearby.setSelected(false);
                btnJar.setSelected(true);
                btnChat.setSelected(false);
                break;

            case R.id.ll_chat:
                setupBottomBar(2);
                btnNearby.setSelected(false);
                btnJar.setSelected(false);
                btnChat.setSelected(true);
                break;
        }
    }
}
