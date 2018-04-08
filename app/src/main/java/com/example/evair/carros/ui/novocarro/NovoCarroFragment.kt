package com.example.evair.carros.ui.novocarro


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.evair.carros.R
import com.example.evair.carros.api.ProdutoApi
import com.example.evair.carros.api.RetrofitClient
import com.example.evair.carros.model.Produto
import com.example.evair.carros.ui.listacarros.ListaCarrosFragment
import com.example.evair.carros.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_novo_carro.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class NovoCarroFragment : Fragment() {

    var produto:Produto = Produto("","","",0, "")

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_novo_carro, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(!produto.id.isNullOrEmpty()){
            inputNomeProd?.editText?.setText(produto.nome)
            inputCategoriaProd?.editText?.setText(produto.categoria)
            //inputPrecoProd?.editText?.
            inputImagemProd?.editText?.setText(produto.urlImagem)
            btnDeletar.visibility = View.VISIBLE
        } else {
            btnDeletar.visibility = View.INVISIBLE
        }


        btSalvar.setOnClickListener {
            val api = RetrofitClient.getInstance().create(ProdutoApi::class.java)

            if(produto.id.isNullOrEmpty()) {
                val carro = Produto(null,
                        inputNomeProd?.editText?.text.toString(),
                        inputCategoriaProd?.editText?.text.toString(),
                        inputPrecoProd.editText?.text.toString().toInt(),
                        inputImagemProd.editText?.text.toString())

                api.salvar(carro).enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                        if (response?.isSuccessful == true) {
                            Toast.makeText(context, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show()
                            limparCampos()
                        } else {
                            Toast.makeText(context, "Errou fausto", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Void>?, t: Throwable?) {
                        Log.e("CARRO", t?.message)
                    }

                })
            } else {
                val carro = Produto(produto.id,
                        inputNomeProd?.editText?.text.toString(),
                        inputCategoriaProd?.editText?.text.toString(),
                        inputPrecoProd.editText?.text.toString().toInt(),
                        inputImagemProd.editText?.text.toString())

                api.update(carro).enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                        if (response?.isSuccessful == true) {
                            Toast.makeText(context, "Alterado com sucesso", Toast.LENGTH_SHORT).show()
                            limparCampos()
                            var activity = context as MainActivity
                            activity.changeFragment(ListaCarrosFragment())
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

        btnDeletar.setOnClickListener{
            val api = RetrofitClient.getInstance().create(ProdutoApi::class.java)

            if(!produto.id.isNullOrEmpty()){
                api.delete(produto.id!!).enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                        if (response?.isSuccessful == true) {
                            Toast.makeText(context, "Deletado com sucesso", Toast.LENGTH_SHORT).show()
                            limparCampos()
                            var activity = context as MainActivity
                            activity.changeFragment(ListaCarrosFragment())
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
    }

    fun limparCampos(){
        produto.id = null
        inputNomeProd?.editText?.text?.clear()
        inputCategoriaProd?.editText?.text?.clear()
        inputPrecoProd.editText?.text?.clear()
        inputImagemProd.editText?.text?.clear()
    }

}// Required empty public constructor
