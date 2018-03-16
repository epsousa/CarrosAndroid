package com.example.evair.carros.ui.novocarro


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.evair.carros.R
import com.example.evair.carros.api.CarroAPI
import com.example.evair.carros.api.RetrofitClient
import com.example.evair.carros.model.Carro
import kotlinx.android.synthetic.main.fragment_novo_carro.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class NovoCarroFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_novo_carro, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btSalvar.setOnClickListener {
            val api = RetrofitClient.getInstance().create(CarroAPI::class.java)

            val carro = Carro(null,
                    inputMarca?.editText?.text.toString(),
                    inputModelo?.editText?.text.toString(),
                    inputAno.editText?.text.toString().toInt(),
                    inputPlaca.editText?.text.toString(),
                    null)

            api.salvar(carro).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                    if (response?.isSuccessful == true) {
                        Toast.makeText(context, "Sucesso", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Errou fausto", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>?, t: Throwable?) {
                    Log.e("CARRO", t?.message)
                }

            })
        }
    }

}// Required empty public constructor
