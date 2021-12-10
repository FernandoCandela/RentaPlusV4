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
import com.example.rentaplusv10.model.Inmueble

class InmuebleListAdapter(
    private val inmuebleList : List<Inmueble>,
    private val fragment: Fragment,
    private val listener : (Inmueble) -> Unit) :
    RecyclerView.Adapter<InmuebleListAdapter.ViewHolder>() {

    class ViewHolder(view : View, val listener: (Inmueble) -> Unit,val inmuebleList : List<Inmueble>) : RecyclerView.ViewHolder(view),
        View.OnClickListener{

        val iviInmuebleImage : ImageView
        val tviInmuebleName : TextView
        val tviInmuebleAdress : TextView

        init{
            iviInmuebleImage = view.findViewById(R.id.iviInmuebleImage)
            tviInmuebleName= view.findViewById(R.id.tviInmuebleName)
            tviInmuebleAdress = view.findViewById(R.id.tviInmuebleAdress)
            view.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            listener(inmuebleList[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).
        inflate(R.layout.item_inmueble,parent,false)
        val viewholder = ViewHolder(view,listener,inmuebleList)
        return viewholder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tviInmuebleName.text = inmuebleList[position].titulo
        holder.tviInmuebleAdress.text = inmuebleList[position].direccion

        Glide
            .with(fragment)
            .load(inmuebleList[position].url)
            .override(200,100)
            .centerCrop()
            .placeholder(R.drawable.logo)
            .into(holder.iviInmuebleImage)


    }

    override fun getItemCount(): Int {
        return inmuebleList.size
    }
}