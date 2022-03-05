package com.example.perludilindungi

import android.content.Context
import android.content.Intent
import android.view.Menu
import com.example.perludilindungi.ui.bookmark.BookmarkActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

interface IUseBottomNav {
    fun setUpActiveMenu(menu: Menu, pageIdx: Int){
        menu.getItem(pageIdx).isChecked = true
    }

    fun setUpMenuItemListener(bottomNav: BottomNavigationView, context: Context, currentPageIdx:Int){
        bottomNav.setOnNavigationItemSelectedListener { item ->
            var intent: Intent? = null
            when (item.itemId) {
                R.id.nav_host_fragment_activity_main -> {
                    if (currentPageIdx==0) return@setOnNavigationItemSelectedListener true
                    intent = Intent(context, MainActivity::class.java)
                }
                R.id.nav_host_fragment_activity_bookmark -> {
                    if (currentPageIdx==1) return@setOnNavigationItemSelectedListener true
                    intent = Intent(context, BookmarkActivity::class.java)
                }
            }
            intent?.let{it -> context.startActivity(it)}
            return@setOnNavigationItemSelectedListener true
        }
    }
}