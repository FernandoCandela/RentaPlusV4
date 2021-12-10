package com.example.rentaplusv10.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rentaplusv10.R
import com.example.rentaplusv10.model.Historial

class HistorialListAdapter(
    private val historialList: List<Historial>,
    private val fragment: Fragment,
    private val listener: (Historial) -> Unit
) :
    RecyclerView.Adapter<HistorialListAdapter.ViewHolder>() {

    class ViewHolder(
        view: View, val listener: (Historial) -> Unit,
        val historialList: List<Historial>
    ) : RecyclerView.ViewHolder(view), View.OnClickListener {

        val tviArrendatario: TextView
        val tviMonto: TextView
        val tvifecha: TextView
        val iviImg: ImageView

        init {
            iviImg = view.findViewById(R.id.iviHistorial)
            tviArrendatario = view.findViewById(R.id.tviArrendatario)
            tviMonto = view.findViewById(R.id.tviMonto)
            tvifecha = view.findViewById(R.id.tvifecha)
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener(historialList[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_historial, parent, false)

        val viewHolder = ViewHolder(view, listener, historialList)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tviArrendatario.text = historialList[position].arrendatario
        holder.tviMonto.text = historialList[position].monto.toString()
        holder.tvifecha.text = historialList[position].fecha_pago

        Glide.with(fragment)
            .load(historialList[position].url)
            .override(600, 200)
            .fitCenter()
            .into(holder.iviImg)
    }

    override fun getItemCount(): Int {
        return historialList.size
    }