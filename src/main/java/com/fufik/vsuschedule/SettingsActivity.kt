package com.fufik.vsuschedule

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.preference.ListPreference
import android.preference.Preference
import android.preference.PreferenceManager
import android.support.v4.app.NavUtils
import android.support.v7.app.ActionBar
import android.view.MenuItem

class SettingsActivity : AppCompatPreferenceActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_Settings)
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences)
        setupActionBar()
    }

    private fun setupActionBar() {
        val actionBar = getSupportActionBar()
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onMenuItemSelected(featureId: Int, item: MenuItem): Boolean {
    val id = item.itemId
    if (id == android.R.id.home) {
        this.onBackPressed()
        return true
    }
        return super.onMenuItemSelected(featureId, item)
    }
    override fun onBackPressed() {
        setResult(RESULT_OK)
        super.onBackPressed()
        return
    }

    private fun isXLargeTablet(context: Context): Boolean {
        return context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_XLARGE
    }

}