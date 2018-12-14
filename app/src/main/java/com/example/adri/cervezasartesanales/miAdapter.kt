package com.example.adri.cervezasartesanales

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.support.v7.widget.CardView
import android.view.View
import android.widget.ImageView
import com.example.adri.cervezasartesanales.R.mipmap.*

class miAdapter(private val listCervezas: ArrayList<Cervezas>) :

    RecyclerView.Adapter<miAdapter.cervezasViewHolder>() {

    override fun getItemCount(): Int {
        return listCervezas.size
    }

    override fun onBindViewHolder(p0: cervezasViewHolder, p1: Int) {
        p0.marca.text = listCervezas[p1].marca
        p0.nombre.text = listCervezas[p1].nombre
        p0.volumen.text = "Volumen: "+listCervezas[p1].volumen
        p0.precio.text = "Precio: "+listCervezas[p1].precio.toString()
        when {
            listCervezas[p1].fotoId == 0.toLong() -> p0.marcaFoto.setImageResource(defaultbirra)
            listCervezas[p1].fotoId == 1.toLong() -> p0.marcaFoto.setImageResource(portolobo)
            listCervezas[p1].fotoId == 2.toLong() -> p0.marcaFoto.setImageResource(alpujarra)
            listCervezas[p1].fotoId == 3.toLong() -> p0.marcaFoto.setImageResource(mammooth)
            listCervezas[p1].fotoId == 4.toLong() -> p0.marcaFoto.setImageResource(kirin)
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): cervezasViewHolder {
        val v = LayoutInflater.from(p0.getContext()).inflate(R.layout.cervezaview, p0, false)
        return cervezasViewHolder(v)
    }

    class cervezasViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var cv: CardView = itemView.findViewById(R.id.cv)
        internal var volumen: TextView=itemView.findViewById(R.id.volumen)
        internal var precio: TextView= itemView.findViewById(R.id.precio)
        internal var marca: TextView= itemView.findViewById(R.id.marca)
        internal var nombre: TextView= itemView.findViewById(R.id.nombre)
        internal var marcaFoto: ImageView= itemView.findViewById(R.id.marcaFoto) as ImageView
    }

}