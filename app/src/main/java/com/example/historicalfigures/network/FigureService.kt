package com.example.historicalfigures.network

import retrofit2.Call
import com.example.historicalfigures.model.FigureApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FigureService {
    @GET("historicalfigures")
    fun fetchPersonList(@Query("name") name: String):Call<List<FigureApiResponse>>
    }
