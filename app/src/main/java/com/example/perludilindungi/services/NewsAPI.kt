package com.example.perludilindungi.services

import com.example.perludilindungi.models.news.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface NewsAPI {
    @GET("api/get-news")
    fun getNews() : Call<NewsResponse>
}