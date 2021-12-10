package com.example.rentaplusv10

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rentaplusv10.fragments.InmuebleDetailFragment
import com.example.rentaplusv10.model.Arrendatario


class InmuebleDetailActivity : AppCompatActivity(),InmuebleDetailFragment.OnInmuebleClicked {

    private lateinit var idInmueble: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         idInmueble = intent.getBundleExtra("data")?.getString("inmueble").toString()
        setContentView(R.layout.activity_inmueble_detail)


        Log.i("texto",idInmueble)

        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.flaInmuebleDetail, InmuebleDetailFragment(idInmueble))
        ft.commit()
    }

    override fun onEditInmuebleClick() {
        val intent: Intent = Intent()
        intent.setClass(this, EditInmuebleActivity::class.java)
        val bundle: Bundle = Bundle()
        bundle.putString("inmueble", idInmueble )
        intent.putExtra("data", bundle)
        startActivity(intent)
    }

    override fun onEditInquilinoClick(arrendatario: Arrendatario) {

        val intent: Intent = Intent()
        intent.setClass(this, InmuebleDetailActivity::class.java)
        val bundle: Bundle = Bundle()
        bundle.putString("inmueble", idInmueble )
        bundle.putString("arrendatario", arrendatario.id)
        bundle.putString("usuario", arrendatario.id)
        intent.putExtra("data", bundle)
        startActivity(intent)
    }

    override fun onAddFacturaClick(arrendatario: Arrendatario) {
        val intent: Intent = Intent()
        intent.setClass(this, AddFacturaActivity::class.java)
        val bundle: Bundle = Bundle()
        bundle.putString("inmueble", idInmueble )
        bundle.putString("arrendatario", arrendatario.nombres + " " + arrendatario.apellidos)
        intent.putExtra("data", bundle)
        startActivity(intent)
    }

    override fun onAgregarInquilino() {
        val intent: Intent = Intent()
        intent.setClass(this, AddInquilinoActivity::class.java)
        startActivity(intent)
    }


}