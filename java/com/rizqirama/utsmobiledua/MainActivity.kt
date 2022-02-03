package com.rizqirama.utsmobiledua

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rizqirama.utsmobiledua.ui.fragment.DashboardFragment
import com.rizqirama.utsmobiledua.ui.fragment.DataFragment
import com.rizqirama.utsmobiledua.ui.fragment.NotificationFragment

class MainActivity : AppCompatActivity() {

    private lateinit var menu : Menu
    private lateinit var menuItem : MenuItem
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpBottomNav()
        callFragment(0, DataFragment())
    }

    private fun setUpBottomNav() {
        bottomNavigationView = findViewById(R.id.nav_view)
        menu = bottomNavigationView.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.navigation_data -> {
                    callFragment(0, DataFragment())
                }
                R.id.navigation_dashboard -> {
                    callFragment(1, DashboardFragment())
                }
                R.id.navigation_notifications -> {
                    callFragment(2, NotificationFragment())
                }
            }
            false
        }
    }

    private fun callFragment(int: Int, fragment: Fragment) {
        menuItem = menu.getItem(int)
        menuItem.isChecked = true
        callFragment(fragment)
    }

    private fun callFragment(f: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, f)
            .commit()
    }
}