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

class EditInmuebleActivity : AppCompatActivity() {
    private lateinit var idInmueble: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_inmueble)
        val idUser = FirebaseAuth.getInstance().currentUser!!.uid

        idInmueble = intent.getBundleExtra("data")?.getString("inmueble").toString()

        val eteTitulo = findViewById<EditText>(R.id.eteTitulo)
        val eteDireccion = findViewById<EditText>(R.id.eteDireccion)

        val btnGuardarEditarInmueble: Button = findViewById(R.id.btnGuardarEditarInmueble)
        btnGuardarEditarInmueble.setOnClickListener{ _: View ->
            InmuebleManager().editInmueble(Inmueble(idInmueble,eteDireccion.text.toString(),idUser,eteTitulo.text.toString(),""),{
                Toast.makeText(this, "Inmueble actualizado" , Toast.LENGTH_SHORT).show()
            },{ error ->
                Toast.makeText(this, "Error: " + error, Toast.LENGTH_SHORT).show()
            })
        }


    }
}