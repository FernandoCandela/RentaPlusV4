package com.example.rentaplusv10

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.rentaplusv10.model.Arrendatario
import com.example.rentaplusv10.model.Inmueble
import com.example.rentaplusv10.model.InmuebleManager
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text
import java.lang.Long.parseLong

class AddInquilinoActivity : AppCompatActivity() {
    private lateinit var idInmueble: String
    private val fileResult = 1
    private var photoUri = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_arrendatario)
        val idUser = FirebaseAuth.getInstance().currentUser!!.uid

        idInmueble = intent.getBundleExtra("data")?.getString("inmueble").toString()

        val tviMonto= findViewById<EditText>(R.id.tviMonto)
        val tviNombres = findViewById<EditText>(R.id.tviNombres)
        val tviApellidos = findViewById<EditText>(R.id.tviApellidos)
        val tviEmail = findViewById<EditText>(R.id.tviEmail)
        val tviTelefono = findViewById<EditText>(R.id.tviTelefono)
        val tviFechaPago = findViewById<EditText>(R.id.tviFechaPago)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        val img = findViewById<ImageView>(R.id.iviFotoArr)
        btnGuardar.setOnClickListener{ _: View ->


            InmuebleManager().changetoinactivo({
                InmuebleManager().addArrendatario(Arrendatario("",true,tviApellidos.text.toString(),
                    tviEmail.text.toString(),tviFechaPago.text.toString(),idInmueble,parseLong(tviMonto.text.toString()),
                    tviNombres.text.toString(),tviTelefono.text.toString()),{
                    Toast.makeText(this, "Arrendatario creado" , Toast.LENGTH_SHORT).show()
                },{error ->
                    Toast.makeText(this, "Error: " + error, Toast.LENGTH_SHORT).show()})
            },{ error ->
                Toast.makeText(this, "Error: " + error, Toast.LENGTH_SHORT).show()
            })
        }

        Glide
            .with(this)
            .load(photoUri)
            .centerCrop()
            .placeholder(R.drawable.profile_photo)
            .into(img)
    }
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
    }

}