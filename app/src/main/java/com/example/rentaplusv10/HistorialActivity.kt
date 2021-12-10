package com.example.rentaplusv10

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.rentaplusv10.fragments.HistorialListFragment
import com.example.rentaplusv10.fragments.InmueblesFragment
import com.example.rentaplusv10.model.Inmueble
import com.google.firebase.auth.FirebaseAuth

class HistorialActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val fragments = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        val idInmueble = intent.getBundleExtra("data")?.getString("inmueble").toString()

        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.flaHistorial,HistorialListFragment(idInmueble))
        ft.commit()
    }

}