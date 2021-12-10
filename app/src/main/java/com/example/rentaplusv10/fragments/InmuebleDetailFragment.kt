package com.example.rentaplusv10.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.rentaplusv10.R
import com.example.rentaplusv10.adapter.InmuebleListAdapter
import com.example.rentaplusv10.model.Arrendatario
import com.example.rentaplusv10.model.Inmueble
import com.example.rentaplusv10.model.InmuebleManager

class InmuebleDetailFragment(val idInmueble: String) : Fragment() {

    interface OnInmuebleClicked {
        fun onEditInmuebleClick()
        fun onEditInquilinoClick()
        fun onAddFacturaClick()
    }

    private var listener: OnInmuebleClicked? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? OnInmuebleClicked
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_inmueble_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // val idUser = FirebaseAuth.getInstance().currentUser!!.uid
        val idUser = "KMtDBEHnWSPVYVizFZ7Mzd6jOgI2"
        print(idUser)


        InmuebleManager().getInmuebleById(idInmueble, { inm: Inmueble ->
            val tviInmuebleTitle = view.findViewById<TextView>(R.id.tviInmuebleTitle)
            tviInmuebleTitle.text = inm.titulo
            val tviInmuebleDireccion = view.findViewById<TextView>(R.id.tviInmuebleDireccion)
            tviInmuebleDireccion.text = inm.direccion
//
            val butEditInmuebleDetail = view.findViewById<Button>(R.id.butEditInmuebleDetail)
            butEditInmuebleDetail.setOnClickListener { _: View ->
                listener?.onEditInmuebleClick()
            }
//
        }, { error ->
            Toast.makeText(activity, "Error: " + error, Toast.LENGTH_SHORT).show()
        })
        InmuebleManager().getArrendatarioActivo(idInmueble,{ arr: Arrendatario ->
            val tviInquilinoActualName = view.findViewById<TextView>(R.id.tviInquilinoActualName)
            tviInquilinoActualName.text = arr.nombre + " " + arr.apellidos
            val tviDeudaInquilinoActual = view.findViewById<TextView>(R.id.tviDeudaInquilinoActual)
            tviDeudaInquilinoActual.text = arr.monto.toString()
            val tviFechaPagoInquilinoActual = view.findViewById<TextView>(R.id.tviFechaPagoInquilinoActual)
            tviFechaPagoInquilinoActual.text = arr.fecha_pago
            val butEditarInquilinoActual = view.findViewById<Button>(R.id.butEditarInquilinoActual)
            butEditarInquilinoActual.setOnClickListener{ _:View ->
                listener?.onEditInquilinoClick()
            }

            val butAgregarFactura = view.findViewById<Button>(R.id.butAgregarFactura)
            butAgregarFactura.setOnClickListener{ _ : View ->
                listener?.onAddFacturaClick()
            }

        }, { error ->
            Toast.makeText(activity, "Error: " + error, Toast.LENGTH_SHORT).show()
        })
    }
}