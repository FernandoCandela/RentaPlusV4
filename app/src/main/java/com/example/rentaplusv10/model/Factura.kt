package com.example.rentaplusv10.model

import java.io.Serializable

data class Factura(
    val id: String,
    val fecha_pago: String,
    val monto: Float
) : Serializable