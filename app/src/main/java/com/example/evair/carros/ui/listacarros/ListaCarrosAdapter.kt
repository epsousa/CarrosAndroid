package com.example.evair.carros.ui.listacarros

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.evair.carros.R
import com.example.evair.carros.model.Carro
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_carro.view.*


class ListaCarrosAdapter(private val carros: List<Carro>, private val context: Context) : RecyclerView.Adapter<ListaCarrosAdapter.MeuViewHolder>() {

    override fun getItemCount(): Int {
        return carros.size
    }

    override fun onBindViewHolder(holder: MeuViewHolder?, position: Int) {
        val carro = carros[position]

        holder?.let {
            it.bindView(carro)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MeuViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.item_carro, parent, false)

        return MeuViewHolder(view)
    }

    class MeuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(carro: Carro) {
            itemView.tvMarca.text = carro.marca
            itemView.tvModelo.text = carro.modelo
            Picasso.get().load(carro.urlImagem).placeholder(R.drawable.car).error(R.drawable.erro).into(itemView.ivFoto);
        }
    }
}