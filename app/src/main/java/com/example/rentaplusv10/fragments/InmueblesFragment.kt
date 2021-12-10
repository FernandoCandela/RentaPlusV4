package com.example.rentaplusv10.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.rentaplusv10.R
import com.example.rentaplusv10.adapter.InmuebleListAdapter
import com.example.rentaplusv10.model.Inmueble
import com.example.rentaplusv10.model.InmuebleManager
import com.google.firebase.auth.FirebaseAuth

class InmueblesFragment  : Fragment(){

    interface OnInmuebleSelectedListener {
        fun onSelect(inmueble: Inmueble)
    }

    private var listener: OnInmuebleSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? OnInmuebleSelectedListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_inmuebles,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // val idUser = FirebaseAuth.getInstance().currentUser!!.uid
        val idUser="KMtDBEHnWSPVYVizFZ7Mzd6jOgI2"
        print(idUser)
        InmuebleManager().getInmueblesByUser(idUser,{ vgList : MutableList<Inmueble> ->
            val rviInmuebles = view.findViewById<RecyclerView>(R.id.rviInmuebles)
            rviInmuebles.adapter = InmuebleListAdapter(
                vgList)
            {
                    inm: Inmueble  ->
                listener?.onSelect(inm)
            }

        },{error ->
            Toast.makeText(activity, "Error: " + error, Toast.LENGTH_SHORT).show()
        })
    }
}