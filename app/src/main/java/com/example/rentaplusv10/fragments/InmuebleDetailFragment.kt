package com.example.rentaplusv10.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.rentaplusv10.R
import com.example.rentaplusv10.model.Arrendatario
import com.example.rentaplusv10.model.Inmueble
import com.example.rentaplusv10.model.InmuebleManager
import com.google.firebase.auth.FirebaseAuth

class InmuebleDetailFragment(val idInmueble: String) : Fragment() {

    interface OnInmuebleClicked {
        fun onEditInmuebleClick()
        fun onEditInquilinoClick(arrendatario: Arrendatario)
        fun onAddFacturaClick(arrendatario: Arrendatario)
        fun onAgregarInquilino()
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

        val idUser = FirebaseAuth.getInstance().currentUser!!.uid
        //val idUser = "KMtDBEHnWSPVYVizFZ7Mzd6jOgI2"
        print(idUser)


        InmuebleManager().getInmuebleById(idInmueble, { inm: Inmueble ->
            val tviInmuebleTitle = view.findViewById<TextView>(R.id.tviInmuebleTitle)
            tviInmuebleTitle.text = inm.titulo
            val tviInmuebleDireccion = view.findViewById<TextView>(R.id.tviInmuebleDireccion)
            tviInmuebleDireccion.text = inm.direccion
            val imgIn = view.findViewById<ImageView>(R.id.iviInmueblePhoto)
            Glide
                .with(this)
                .load(inm.url)
                .override(200,100)
                .centerCrop()
                .placeholder(R.drawable.mapa)
                .into(imgIn)

            val butEditInmuebleDetail = view.findViewById<Button>(R.id.butEditInmuebleDetail)
            butEditInmuebleDetail.setOnClickListener { _: View ->
                listener?.onEditInmuebleClick()
            }

        }, { error ->
            Toast.makeText(activity, "Error: " + error, Toast.LENGTH_SHORT).show()
        })
        InmuebleManager().getArrendatarioActivo(idInmueble,{ arr: Arrendatario? ->
            val tviInquilinoActualName = view.findViewById<TextView>(R.id.tviInquilinoActualName)
            val tviDeudaInquilinoActual = view.findViewById<TextView>(R.id.tviDeudaInquilinoActual)
            val tviFechaPagoInquilinoActual = view.findViewById<TextView>(R.id.tviFechaPagoInquilinoActual)
            val butEditarInquilinoActual = view.findViewById<Button>(R.id.butEditarInquilinoActual)
            val butAgregarFactura = view.findViewById<Button>(R.id.butAgregarFactura)
            val btnAgregarInquilino = view.findViewById<ImageView>(R.id.btnAgregarInquilino)

            if(arr == null){
                tviInquilinoActualName.text = ""

                tviDeudaInquilinoActual.text = ""

                tviFechaPagoInquilinoActual.text = ""

                btnAgregarInquilino.setOnClickListener{ _ : View ->
                    listener?.onAgregarInquilino()
                }
            }else{


            tviInquilinoActualName.text = arr.nombres + " " + arr.apellidos

            tviDeudaInquilinoActual.text = arr.monto.toString()

            tviFechaPagoInquilinoActual.text = arr.fecha_pago

            butEditarInquilinoActual.setOnClickListener{ _:View ->
                listener?.onEditInquilinoClick(arr)
            }


            butAgregarFactura.setOnClickListener{ _ : View ->
                listener?.onAddFacturaClick(arr)
            }

            btnAgregarInquilino.setOnClickListener{ _ : View ->
                listener?.onAgregarInquilino()
            }

            }

        }, { error ->
            Toast.makeText(activity, "Error: " + error, Toast.LENGTH_SHORT).show()
        })
    }
}