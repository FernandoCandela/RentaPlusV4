package com.example.rentaplusv10.model

import java.io.Serializable

data class Historial(
    val id: String,
    val arrendatario: String,
    val monto: Long,
    val fecha_pago: String,
    val idInmueble: String,
    val url: String
) : Serializable