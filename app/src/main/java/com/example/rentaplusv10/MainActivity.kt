package com.example.rentaplusv10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.rentaplusv10.fragments.InmueblesFragment
import com.example.rentaplusv10.model.Inmueble
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() , InmueblesFragment.OnInmuebleSelectedListener{

    private val fragments = mutableListOf<Fragment>()
    private lateinit var dlaMain : DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragments.add(InmueblesFragment())

        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.flaContent,fragments[0])
        ft.commit()
    }


    override fun onSelect(inmueble: Inmueble) {
        Log.i("text","click");
        Log.i("texto",inmueble.direccion)
        val intent: Intent = Intent()
        intent.setClass(this, InmuebleDetailActivity::class.java)
        val bundle: Bundle = Bundle()
        bundle.putString("inmueble", inmueble.id )
        intent.putExtra("data", bundle)
        startActivity(intent)
    }

}