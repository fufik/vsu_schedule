package com.fufik.vsuschedule

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity: AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener,DrawerLayout.DrawerListener {
    private var day: Int = 0
    private var group: Int = 0
    private var week: Boolean = false
    private val paras: ArrayList<Para> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val intent = intent
            day = intent.getIntExtra("DayOfWeek", Calendar.getInstance().get(Calendar.DAY_OF_WEEK))
            PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
            val pManager = PreferenceManager.getDefaultSharedPreferences(this)
            group = intent.getIntExtra("Group", Integer.parseInt(pManager.getString("group_list", null)))
            week = intent.getBooleanExtra("Week", Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) % 2 == 0) //t -- num, f -- den
        } else {
            day = savedInstanceState.getInt("DayOfWeek")
            group = savedInstanceState.getInt("Group")
            week = savedInstanceState.getBoolean("Week")
        }


        val llm = LinearLayoutManager(this)
        val rv = findViewById(R.id.rv) as RecyclerView
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        rv.setLayoutManager(llm)
        rv.setHasFixedSize(true)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        drawer.addDrawerListener(this)
        toggle.syncState()
        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
        val header = navigationView.getHeaderView(0)
        val tvGroup = header.findViewById<TextView>(R.id.group)
        tvGroup.text = group.toString() + " " + getString(R.string.group)
        if (day == Calendar.MONDAY) {
            navigationView.menu.getItem(0).isChecked = true
        } else if (day == Calendar.TUESDAY) {
            navigationView.menu.getItem(1).isChecked = true
        } else if (day == Calendar.WEDNESDAY) {
            navigationView.menu.getItem(2).isChecked = true
        } else if (day == Calendar.THURSDAY) {
            navigationView.menu.getItem(3).isChecked = true
        } else if (day == Calendar.FRIDAY) {
            navigationView.menu.getItem(4).isChecked = true
        } else if (day == Calendar.SATURDAY) {
            navigationView.menu.getItem(5).isChecked = true
        } else if (day == Calendar.SUNDAY) {
            navigationView.menu.getItem(6).isChecked = true
        }

        prepareSchedule(day)
    }


    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            val settingsIntent = Intent(baseContext, SettingsActivity::class.java)
            settingsIntent.putExtra("Group", group)
            startActivityForResult(settingsIntent, Activity.RESULT_OK)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val pManager = PreferenceManager.getDefaultSharedPreferences(this)
        group = Integer.parseInt(pManager.getString("group_list", null))

    }

    override fun onResume() {
        super.onResume()
        prepareSchedule(day)

    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_monday) {
            day = Calendar.MONDAY
        } else if (id == R.id.nav_tuesday) {
            day = Calendar.TUESDAY
        } else if (id == R.id.nav_wednesday) {
            day = Calendar.WEDNESDAY
        } else if (id == R.id.nav_thursday) {
            day = Calendar.THURSDAY
        } else if (id == R.id.nav_friday) {
            day = Calendar.FRIDAY
        } else if (id == R.id.nav_saturday) {
            day = Calendar.SATURDAY
        } else if (id == R.id.nav_rschedule) {
            day = Calendar.SUNDAY
        }
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        prepareSchedule(day)
        return true
    }

    override fun onDrawerOpened(drawerView: View) {
        updateHeader()
    }

    override fun onDrawerClosed(drawerView: View) {
        updateHeader()
    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}

    override fun onDrawerStateChanged(newState: Int) {

    }

    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putInt("DayOfWeek", day)
        savedInstanceState.putInt("Group", group)
        savedInstanceState.putBoolean("Week", week)
        super.onSaveInstanceState(savedInstanceState)
    }


    private fun initializeData(day: Int) {
        paras.clear()
        if (Schedules.groups.containsKey(group)) {
            val schedule = Schedules.groups[group]!![Schedules.getKey(day)] ?: return
            for (i in schedule) {
                if (i.state === ParaState.Usual) {
                    paras.add(i)
                } else {
                    if (i.state === ParaState.Numerator) {
                        if (week) {
                            paras.add(i)
                        }
                    } else {
                        //i.state = Denominator
                        if (!week) {
                            paras.add(i)
                        }
                    }
                }
            }
        }
    }


    private fun initializeAdapter() {
        val adapter = RecyclerAdapter(paras)
        val rv = findViewById(R.id.rv) as RecyclerView
        rv.setAdapter(adapter)
    }

    private fun prepareSchedule(day: Int) {
        val pManager = PreferenceManager.getDefaultSharedPreferences(this)
        group = Integer.parseInt(pManager.getString("group_list", null))
        initializeData(day)
        initializeAdapter()
    }

    private fun updateHeader() {
        val navigationView = findViewById(R.id.nav_view) as NavigationView
        val header = navigationView.getHeaderView(0)
        val tvGroup = header.findViewById<TextView>(R.id.group)
        tvGroup.text = group.toString() + " " + getString(R.string.group)
    }
}