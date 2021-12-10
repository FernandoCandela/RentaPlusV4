package com.example.rentaplusv10.model

import java.io.Serializable

class Inmueble (
    val id: String,
    val direccion: String,
    val idUsuario: String,
    val titulo: String,
    val url: String
    ) : Serializable