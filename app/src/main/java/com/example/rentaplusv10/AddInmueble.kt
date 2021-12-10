package com.example.rentaplusv10

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rentaplusv10.model.Historial
import com.example.rentaplusv10.model.Inmueble
import com.example.rentaplusv10.model.InmuebleManager
import com.google.firebase.auth.FirebaseAuth
import java.lang.Long

class AddInmueble : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_inmueble)

        val idUser = FirebaseAuth.getInstance().currentUser!!.uid

        val tviDireccion = findViewById<EditText>(R.id.tviDireccion)
        val tviTitulo = findViewById<EditText>(R.id.tviTitulo)
        val btnGuardar= findViewById<Button>(R.id.btnGuardar)

        btnGuardar.setOnClickListener{ _: View ->
            InmuebleManager().addInmueble(Inmueble("",tviDireccion.text.toString(),idUser,tviTitulo.text.toString(),""),idUser,{
                Toast.makeText(this, "Inmueble creado" , Toast.LENGTH_SHORT).show()
            },{ error ->
                Toast.makeText(this, "Error: " + error, Toast.LENGTH_SHORT).show()
            })
        }

    }
}