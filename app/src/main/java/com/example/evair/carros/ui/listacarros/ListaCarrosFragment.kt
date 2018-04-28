package com.example.evair.carros.ui.listacarros


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.evair.carros.R
import com.example.evair.carros.api.CarroAPI
import com.example.evair.carros.api.ProdutoApi
import com.example.evair.carros.api.RetrofitClient
import com.example.evair.carros.model.Carro
import com.example.evair.carros.model.Produto
import com.example.evair.carros.ui.novocarro.NovoCarroFragment
import kotlinx.android.synthetic.main.erro.*
import kotlinx.android.synthetic.main.fragment_lista_carros.*
import kotlinx.android.synthetic.main.loading.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class ListaCarrosFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_lista_carros, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        carregarDados()
    }

    fun carregarDados() {
        val api = RetrofitClient.getInstance().create(ProdutoApi::class.java)

        loading.visibility = View.VISIBLE

        api.getAll().enqueue(object : Callback<List<Produto>> {
            override fun onResponse(call: Call<List<Produto>>?, response: Response<List<Produto>>?) {

                containerErro.visibility = View.GONE
                tvMensagemErro.text = ""

                if (response?.isSuccessful == true) {
                    setupLista(response?.body())
                } else {
                    containerErro.visibility = View.VISIBLE
                    tvMensagemErro.text = response?.errorBody()?.charStream()?.readText()
                }

                loading.visibility = View.GONE
            }

            override fun onFailure(call: Call<List<Produto>>?, t: Throwable?) {
                containerErro.visibility = View.VISIBLE
                tvMensagemErro.text = t?.message
                loading.visibility = View.GONE
            }

        })
    }

    fun setupLista(carros: List<Produto>?) {

        if(carros!!.isNotEmpty()) {
            carros.let {
                rvCarros.adapter = ListaCarrosAdapter(carros!!, context)
                val layoutManager = LinearLayoutManager(context)
                rvCarros.layoutManager = layoutManager
            }
        } else {
            Toast.makeText(this.context, R.string.notFoundProducts, Toast.LENGTH_SHORT).show()
        }
    }

}
