package com.example.rentaplusv10.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.rentaplusv10.R
import com.example.rentaplusv10.adapter.HistorialListAdapter
import com.example.rentaplusv10.model.Historial
import com.example.rentaplusv10.model.InmuebleManager

class HistorialListFragment(val idInmueble: String) : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_historial_list,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id = idInmueble
        InmuebleManager().getHistorialByInmueble(id,{ vgList : MutableList<Historial> ->
            val rviPokemon = view.findViewById<RecyclerView>(R.id.rviHistorial)
            rviPokemon.adapter = HistorialListAdapter(
                vgList,
                this
            ){

            }
        },{error ->
            Toast.makeText(activity, "Error: " + error, Toast.LENGTH_SHORT).show()
        })

    }



}