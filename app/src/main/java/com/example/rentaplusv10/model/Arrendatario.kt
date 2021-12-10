package com.example.rentaplusv10.model

import java.io.Serializable

data class Arrendatario(
    val id: String,
    val activo: Boolean,
    val apellidos: String,
    val email: String,
    val fecha_pago: String,
    val idInmueble: String,
    val monto: Long,
    val nombres: String,
    val telefono: String
) : Serializable