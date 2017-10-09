package com.fufik.vsuschedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,DrawerLayout.DrawerListener {
    private RecyclerView rv;
    private ArrayList<Para> paras;
    private int day;
    private int group;
    private boolean week;
    private static SharedPreferences pManager;
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if  (savedInstanceState == null){
            Intent intent = getIntent();
            day = intent.getIntExtra("DayOfWeek",Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
            PreferenceManager.setDefaultValues(this,R.xml.pref_general,false);
            pManager = PreferenceManager.getDefaultSharedPreferences(this);
            String group_str = pManager.getString("group_list",null);
            group = intent.getIntExtra("Group", Integer.parseInt(group_str));
            week = intent.getBooleanExtra("Week",Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)%2==0); //t -- num, f -- den
        } else {
            day = savedInstanceState.getInt("DayOfWeek");
            group = savedInstanceState.getInt("Group");
            week = savedInstanceState.getBoolean("Week");
        }

        rv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        drawer.addDrawerListener(this);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        TextView tvGroup = header.findViewById(R.id.group);
        tvGroup.setText(group + " " + getString(R.string.group));
        if (day == Calendar.MONDAY) {
            navigationView.getMenu().getItem(0).setChecked(true);
        } else if (day == Calendar.TUESDAY) {
            navigationView.getMenu().getItem(1).setChecked(true);
        } else if (day == Calendar.WEDNESDAY) {
            navigationView.getMenu().getItem(2).setChecked(true);
        } else if (day == Calendar.THURSDAY) {
            navigationView.getMenu().getItem(3).setChecked(true);
        } else if (day == Calendar.FRIDAY) {
            navigationView.getMenu().getItem(4).setChecked(true);
        } else if (day == Calendar.SATURDAY) {
            navigationView.getMenu().getItem(5).setChecked(true);
        } else if (day == Calendar.SUNDAY) {
            navigationView.getMenu().getItem(6).setChecked(true);
        }

        prepareSchedule(day);
    }



    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(getBaseContext(),SettingsActivity.class);
            settingsIntent.putExtra("Group",group);
            startActivityForResult(settingsIntent,RESULT_OK);
            group = Integer.parseInt(pManager.getString("group_list",null));
            prepareSchedule(day);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_monday) {
            day = Calendar.MONDAY;
        } else if (id == R.id.nav_tuesday) {
            day = Calendar.TUESDAY;
        } else if (id == R.id.nav_wednesday) {
            day = Calendar.WEDNESDAY;
        } else if (id == R.id.nav_thursday) {
            day = Calendar.THURSDAY;
        } else if (id == R.id.nav_friday) {
            day = Calendar.FRIDAY;
        } else if (id == R.id.nav_saturday) {
            day = Calendar.SATURDAY;
        } else if (id == R.id.nav_rschedule) {
            day = Calendar.SUNDAY;
        }

        drawer.closeDrawer(GravityCompat.START);
        prepareSchedule(day);
        return true;
    }
    @Override
    public void onDrawerOpened(View drawerView){
        updateHeader();
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        updateHeader();
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("DayOfWeek",day);
        savedInstanceState.putInt("Group",group);
        savedInstanceState.putBoolean("Week",week);
        super.onSaveInstanceState(savedInstanceState);
    }


    private void initializeData(int day) {
        paras = new ArrayList<>();
        if (Schedules.groups.containsKey(group)){
            ArrayList<Para> schedule = Schedules.groups.get(group).get(Schedules.getKey(day));
            if (schedule==null) {return;}
            for (Para i:schedule){
                if (i.state == ParaState.Usual){
                    paras.add(i);
                } else {
                    if (i.state == ParaState.Numerator){
                        if(week){
                            paras.add(i);
                        }
                    } else {
                        //i.state = Denominator
                        if (!week){
                            paras.add(i);
                        }
                    }
                }
            }
        }
    }



    private void initializeAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(paras);
        rv.setAdapter(adapter);
    }
    private void prepareSchedule(int day){
        group = Integer.parseInt(pManager.getString("group_list",null));
        initializeData(day);
        initializeAdapter();
    }

    public int getDay() {
        return day;
    }

    private void updateHeader(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        TextView tvGroup = header.findViewById(R.id.group);
        tvGroup.setText(group + " " + getString(R.string.group));
    }
}