package com.example.evair.carros

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.evair.carros.api.LoginApi
import com.example.evair.carros.api.RetrofitClient
import com.example.evair.carros.model.Login

import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Intent
import com.example.evair.carros.ui.main.MainActivity


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)

        btnLogin.setOnClickListener{
            val api = RetrofitClient.getInstance().create(LoginApi::class.java)

            var login = Login("","",inputSenha?.editText?.text.toString(), inputEmail?.editText?.text.toString())
            api.login(login).enqueue(object : Callback<Login> {
                override fun onResponse(call: Call<Login>?, response: Response<Login>?) {
                    if (response?.isSuccessful == true) {
                        if(response.body()?.email != null && !response.body()?.email.equals("")) {
                            try {
                                val k = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(k)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        } else {
                            Toast.makeText(applicationContext, "Email ou Senha incorretos", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Toast.makeText(applicationContext, response?.body().toString(), Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<Login>?, t: Throwable?) {
                    Log.e("CARRO", t?.message)
                    Toast.makeText(applicationContext, t?.message, Toast.LENGTH_SHORT).show()
                }

            })
        }

        btnCadastre.setOnClickListener{
            try {
                val k = Intent(this@LoginActivity, CadastroActivity::class.java)
                startActivity(k)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

//    override fun onCreateView(name: String?, context: Context?, attrs: AttributeSet?): View {
//
//
//
//        btnLogin.setOnClickListener{
//            val api = RetrofitClient.getInstance().create(LoginApi::class.java)
//            var login = Login("","",inputSenha?.editText?.text.toString(), inputEmail?.editText?.text.toString())
//            api.login(login).enqueue(object : Callback<Login> {
//                override fun onResponse(call: Call<Login>?, response: Response<Login>?) {
//                    if (response?.isSuccessful == true) {
//                        Toast.makeText(context, response.body()?.nome, Toast.LENGTH_SHORT).show()
//                    } else {
//                        Toast.makeText(context, "Errou fausto", Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//                override fun onFailure(call: Call<Login>?, t: Throwable?) {
//                    Log.e("CARRO", t?.message)
//                }
//
//            })
//        }
//
//
//        return super.onCreateView(name, context, attrs)
//    }

}
