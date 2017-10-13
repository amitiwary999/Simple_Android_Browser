package com.example.meeera.browserapp.Service

import com.example.meeera.browserapp.Model.Articles
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by meeera on 13/10/17.
 */
interface NewService {
    @GET("v1/articles?")
    fun getNews(@Query("source") source : String, @Query("apiKey") apikey : String ) : Observable<Articles>
}