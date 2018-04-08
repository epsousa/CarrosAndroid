package com.example.evair.carros.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.evair.carros.R
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
                Toast.makeText(this, "Em construção, boa sorte champs!", Toast.LENGTH_LONG).show()
                return@OnNavigationItemSelectedListener true
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
