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
import com.example.evair.carros.api.RetrofitClient
import com.example.evair.carros.model.Carro
import kotlinx.android.synthetic.main.fragment_lista_carros.*
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
        carregarDados()
        return inflater!!.inflate(R.layout.fragment_lista_carros, container, false)
    }

    fun carregarDados() {
        val api = RetrofitClient.getInstance("https://carroapis.herokuapp.com/").create(CarroAPI::class.java)
        api.buscarTodos().enqueue(object : Callback<List<Carro>> {
            override fun onResponse(call: Call<List<Carro>>?, response: Response<List<Carro>>?) {

                if (response?.isSuccessful == true) {
                    setupLista(response?.body())
                }
            }

            override fun onFailure(call: Call<List<Carro>>?, t: Throwable?) {

            }

        })
    }

    fun setupLista(carros: List<Carro>?) {
        carros.let {
            rvCarros.adapter = ListaCarrosAdapter(carros!!, context)
            val layoutManager = LinearLayoutManager(context)
            rvCarros.layoutManager = layoutManager
        }
    }

}// Required empty public constructor
