package com.agus.proyectointegradortm.activities

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agus.proyectointegradortm.MyApplication
import com.agus.proyectointegradortm.R
import com.agus.proyectointegradortm.adapters.TypesAdapter
import com.agus.proyectointegradortm.databinding.ActivityHomeBinding
import com.agus.proyectointegradortm.databinding.NavHeaderMainBinding
import com.agus.proyectointegradortm.models.User
import com.agus.proyectointegradortm.providers.UsersProvider
import com.agus.proyectointegradortm.viewModels.UserViewModel
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView
    private lateinit var binding: ActivityHomeBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        drawerLayout = binding.drawerLayout

        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
        drawerLayout.addDrawerListener(actionBarToggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        actionBarToggle.syncState()

        val user = userViewModel.getUserById(MyApplication.preferences.getUserID())
        navView = binding.navView
        navView.setNavigationItemSelectedListener(this)
        val headerView = navView.getHeaderView(0)
        val headerBinding = NavHeaderMainBinding.bind(headerView)
        Glide.with(view).load(user.profileImage).into(headerBinding.ivNavHeaderImage)
        headerBinding.tvNavHeaderTitle.text = user.userName + " " + user.userSurname

        // Recycler view con los 5 items
        val recyclerView: RecyclerView = binding.rvItemTypes
        val adapter = TypesAdapter()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        adapter.onItemClick = {
            val intent = Intent(this, ItemsActivity::class.java)
            intent.putExtra("type", it)
            startActivity(intent)
        }

        binding.fabShoppingCart.setOnClickListener {
            goTo(CartActivity::class.java)
        }
    }

    // Funcion para cuando se elige un elemento
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.historyOption -> goTo(HistoryActivity::class.java)
            R.id.myProfileOption -> goTo(ProfileActivity::class.java)
            R.id.settingsOption -> goTo(SettingsActivity::class.java)
            R.id.orderOption -> goTo(OrderActivity::class.java)
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    // Sobreescribe el metodo para abrir el Drawer cuando el boton hamburguesa es presionado
    override fun onSupportNavigateUp(): Boolean {
        drawerLayout.openDrawer(navView)
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        actionBarToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        actionBarToggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun goTo(activity: Class<*>) {
        val intent = Intent(this, activity)
        startActivity(intent)
    }

    // Se sobreescribe el metodo para cerrar la aplicacion
    override fun onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            finishAffinity()
        }
    }
}