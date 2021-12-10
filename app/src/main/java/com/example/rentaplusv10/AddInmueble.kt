package com.example.rentaplusv10

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.rentaplusv10.model.Inmueble
import com.example.rentaplusv10.model.InmuebleManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*

class AddInmueble : AppCompatActivity() {
    private val fileResult = 1
    private var photoUri = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_inmueble)

        val idUser = FirebaseAuth.getInstance().currentUser!!.uid

        val tviDireccion = findViewById<EditText>(R.id.tviDireccion)
        val tviTitulo = findViewById<EditText>(R.id.tviTitulo)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        val img: ImageView = findViewById(R.id.iviFotFac)
        img.setOnClickListener {
            fileManager()
        }
        btnGuardar.setOnClickListener { _: View ->
            InmuebleManager().addInmueble(
                Inmueble(
                    "",
                    tviDireccion.text.toString(),
                    idUser,
                    tviTitulo.text.toString(),
                    photoUri
                ), idUser, {
                    Toast.makeText(this, "Inmueble creado", Toast.LENGTH_SHORT).show()
                }, { error ->
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

    private fun fileManager() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, fileResult)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == fileResult) {
            if (resultCode == RESULT_OK && data != null) {
                val uri = data.data
                uri?.let { imageUpload(it) }
            }
        }
    }

    private fun imageUpload(mUri: Uri) {
        val uniqueID: String = UUID.randomUUID().toString()
        val folder: StorageReference = FirebaseStorage.getInstance().reference.child("Inmueble")
        val fileName: StorageReference = folder.child("img" + uniqueID)

        fileName.putFile(mUri).addOnSuccessListener {
            fileName.downloadUrl.addOnSuccessListener { uri ->
                photoUri = uri.toString()
                val img: ImageView = findViewById(R.id.iviFotFac)
                Glide
                    .with(this)
                    .load(Uri.parse(photoUri))
                    .centerCrop()
                    .placeholder(R.drawable.profile_photo)
                    .into(img)
            }
        }.addOnFailureListener {
            Log.i("TAG", "file upload error")
        }
    }
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
    }

}