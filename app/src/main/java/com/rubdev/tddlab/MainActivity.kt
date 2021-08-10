package com.rubdev.tddlab

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.edit
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rubdev.tddlab.ContactDetail.Companion.EXTRA_CONTACT

class MainActivity : AppCompatActivity(), ClickItemContactListener {

    private val rvList: RecyclerView by lazy {
        findViewById(R.id.rv_list)
    }
    private val adapter = ContactAdapter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_menu)

        initDrawer()
        fetchListContact()
        bindViews()

    }

    private fun fetchListContact() {
        val list = arrayListOf(
            Contact(
                "Rubens Moura",
                "(85)99999-0000",
                "image1.jpg"
            ),
            Contact(
                "Talita Moura",
                "(85)99999-4444",
                "image1.jpg"
            )
        )
        getInstanceSharedPreferrences().edit {
            val json = Gson().toJson(list)
            putString("contacts", json)
            commit()
        }

    }

    private fun getInstanceSharedPreferrences(): SharedPreferences {
        return getSharedPreferences("com.rubdev.tddlab.PREFERENCES", Context.MODE_PRIVATE)
    }

    private fun initDrawer() {
        val drawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val toogle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer)
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()
    }

    private fun bindViews() {
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this)
        updateList()
    }

    private fun getListContacts(): List<Contact>{
        val list = getInstanceSharedPreferrences().getString("contacts", "[]")
        val turnsType = object : TypeToken<List<Contact>>(){}.type
        return Gson().fromJson(list, turnsType)
    }

    private fun updateList() {
        adapter.updateList(getListContacts())
    }
    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_menu_1 -> {
                showToast("item 1")
                true
            }
            R.id.item_menu_2 -> {
                showToast("item 2")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun clickItemContact(contact: Contact) {
        val intent = Intent(this, ContactDetail::class.java)
        intent.putExtra(EXTRA_CONTACT, contact)
        startActivity(intent)
    }

}