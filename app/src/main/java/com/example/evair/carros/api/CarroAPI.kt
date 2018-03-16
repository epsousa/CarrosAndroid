package com.example.evair.carros.api

import com.example.evair.carros.model.Carro
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CarroAPI {


    @GET("/carro")
    fun buscarTodos(): Call<List<Carro>>

    @POST("/carro")
    fun salvar(@Body carro: Carro): Call<Void>

}