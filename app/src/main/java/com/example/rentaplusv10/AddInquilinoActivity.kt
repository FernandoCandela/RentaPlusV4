package com.example.rentaplusv10

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rentaplusv10.model.Arrendatario
import com.example.rentaplusv10.model.Inmueble
import com.example.rentaplusv10.model.InmuebleManager
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text
import java.lang.Long.parseLong

class AddInquilinoActivity : AppCompatActivity() {
    private lateinit var idInmueble: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_inmueble)
        val idUser = FirebaseAuth.getInstance().currentUser!!.uid

        idInmueble = intent.getBundleExtra("data")?.getString("inmueble").toString()

        val tviMonto= findViewById<EditText>(R.id.tviMonto)
        val tviNombres = findViewById<EditText>(R.id.tviNombres)
        val tviApellidos = findViewById<EditText>(R.id.tviApellidos)
        val tviEmail = findViewById<EditText>(R.id.tviEmail)
        val tviTelefono = findViewById<EditText>(R.id.tviTelefono)
        val tviFechaPago = findViewById<EditText>(R.id.tviFechaPago)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        btnGuardar.setOnClickListener{ _: View ->


            InmuebleManager().changetoinactivo({
            },{ error ->
                Toast.makeText(this, "Error: " + error, Toast.LENGTH_SHORT).show()
            })

            InmuebleManager().addArrendatario(Arrendatario("",true,tviApellidos.text.toString(),
                tviEmail.text.toString(),tviEmail.text.toString(),idInmueble,parseLong(tviMonto.text.toString()),
                tviNombres.text.toString(),tviTelefono.text.toString()),{
                Toast.makeText(this, "Arrendatario creado" , Toast.LENGTH_SHORT).show()
            },{error ->
                Toast.makeText(this, "Error: " + error, Toast.LENGTH_SHORT).show()})
        }
    }
}