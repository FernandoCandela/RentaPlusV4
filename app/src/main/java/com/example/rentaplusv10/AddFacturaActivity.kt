package com.example.rentaplusv10

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rentaplusv10.model.Historial
import com.example.rentaplusv10.model.InmuebleManager
import java.lang.Long.parseLong

class AddFacturaActivity : AppCompatActivity() {
    private lateinit var idInmueble: String
    private lateinit var arrendatario : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_factura)

        idInmueble = intent.getBundleExtra("data")?.getString("inmueble").toString()
        arrendatario = intent.getBundleExtra("data")?.getString("arrendatario").toString()

        val eteFechaPago = findViewById<EditText>(R.id.eteFechaPago)
        val eteMonto = findViewById<EditText>(R.id.eteMonto)

        val butGuardar: Button = findViewById(R.id.btnGuardar)
        butGuardar.setOnClickListener{ _: View ->
            InmuebleManager().addHistorial(Historial("",arrendatario,parseLong(eteMonto.text.toString()),eteFechaPago.text.toString(),idInmueble,""),{
                Toast.makeText(this, "Factura guardada" , Toast.LENGTH_SHORT).show()
            },{ error ->
                Toast.makeText(this, "Error: " + error, Toast.LENGTH_SHORT).show()
            })
        }


    }
}