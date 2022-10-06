package com.example.teachersjournal.weather.network
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("data/2.5/weather?")
    fun getCurrentWeatherData(@Query("lat") lat: String, @Query("lon") lon: String, @Query("APPID") app_id: String): Call<WeatherProperty>
}
//private const val  BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"
/*private const val  BASE_URL =  "https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=50e61c814731ad7495b7ddef6ffc97ac"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
   .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface WeatherApiService {
   // @GET("realestate")
    suspend fun getProperties(): List<WeatherProperty>
}

object WeatherApi {
    val retrofitService : WeatherApiService by lazy { retrofit.create(WeatherApiService::class.java) }
}
*/