package com.example.rentaplusv10

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rentaplusv10.fragments.InmuebleDetailFragment


class InmuebleDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val idInmueble = intent.getBundleExtra("data")?.getString("inmueble").toString()
        setContentView(R.layout.activity_inmueble_detail)


        Log.i("texto",idInmueble)

        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.flaInmuebleDetail, InmuebleDetailFragment(idInmueble))
        ft.commit()
    }


}