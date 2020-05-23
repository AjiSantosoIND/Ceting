package com.chatting.ceting

import android.os.Bundle
import android.view.Menu
import com.chatting.ceting.contact.ContactFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.all_chat)

        menu_bottom.setOnNavigationItemSelectedListener {
            val visibleFragment = when (it.itemId) {
                R.id.contact -> ContactFragment()
                R.id.chat -> ChatFragment()
                else -> ChatFragment()
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, visibleFragment).commit()

            return@setOnNavigationItemSelectedListener false
        }

        menu_bottom.selectedItemId = R.id.chat
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
