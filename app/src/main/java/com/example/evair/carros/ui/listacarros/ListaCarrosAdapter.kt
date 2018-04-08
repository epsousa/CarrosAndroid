package com.example.evair.carros.ui.listacarros

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.evair.carros.R
import com.example.evair.carros.model.Carro
import com.example.evair.carros.model.Produto
import com.example.evair.carros.ui.main.MainActivity
import com.example.evair.carros.ui.novocarro.NovoCarroFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_carro.view.*


class ListaCarrosAdapter(private val carros: List<Produto>, private val context: Context) : RecyclerView.Adapter<ListaCarrosAdapter.MeuViewHolder>() {

    override fun getItemCount(): Int {
        return carros.size
    }

    override fun onBindViewHolder(holder: MeuViewHolder?, position: Int) {
        val carro = carros[position]

        holder?.let {
            it.bindView(carro, context)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MeuViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.item_carro, parent, false)

        return MeuViewHolder(view)
    }

    class MeuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(carro: Produto, context: Context) {

            var activity = context as MainActivity

            itemView.setOnClickListener{
                var frag = NovoCarroFragment()
                frag.produto = carro
                activity.changeFragment(frag)
            }
            itemView.tvMarca.text = carro.nome
            itemView.tvModelo.text = carro.categoria
            if(!carro.urlImagem.isNullOrEmpty()) {
                Picasso.get().load(carro.urlImagem).placeholder(R.drawable.bag).error(R.drawable.erro).into(itemView.ivFoto);
            } else {
                Picasso.get().load("https://w3.siemens.com.br/automation/br/pt/gerenciamento-vida-produto/solucoes-plm-produtos/PublishingImages/plm-produtos.jpg").placeholder(R.drawable.bag).error(R.drawable.erro).into(itemView.ivFoto);
            }
        }
    }
}