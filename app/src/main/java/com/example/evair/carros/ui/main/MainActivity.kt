package com.example.evair.carros.ui.main

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.evair.carros.LoginActivity
import com.example.evair.carros.R
import com.example.evair.carros.fragment_sobre
import com.example.evair.carros.ui.listacarros.ListaCarrosFragment
import com.example.evair.carros.ui.novocarro.NovoCarroFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_lista -> {
                changeFragment(ListaCarrosFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_novo -> {
                changeFragment(NovoCarroFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_sobre -> {
                changeFragment(fragment_sobre())
                return@OnNavigationItemSelectedListener true
            }
            R.id.loggout -> {
                var prefs: SharedPreferences? = applicationContext.getSharedPreferences("LOGIN",0);
                try {
                    prefs?.edit()?.putBoolean("MENTER_LOGADO", false)?.commit()
                    val k = Intent(this@MainActivity, LoginActivity::class.java)
                    startActivity(k)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        false
    }

    fun changeFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.containerFragment, fragment)
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        changeFragment(ListaCarrosFragment())

    }

}
