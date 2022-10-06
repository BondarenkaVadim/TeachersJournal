package com.example.teachersjournal.weather.overview

import android.Manifest
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.location.LocationRequest
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.teachersjournal.R
import com.example.teachersjournal.weather.network.WeatherApiService
import com.example.teachersjournal.weather.network.WeatherProperty
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.fragment_weather.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WeatherFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WeatherFragment : Fragment() {
    private var weatherData: TextView? = null

    //______для локации
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    //------

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED)
                arguments?.let {



                    param1 = it.getString(ARG_PARAM1)
                    param2 = it.getString(ARG_PARAM2)
                }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
/*

f
 */

        getCurrentData()
        //textUrl()
        return inflater.inflate(R.layout.fragment_weather, container, false)

    }

    //___________________
    internal fun getCurrentData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherApiService::class.java)
        val call = service.getCurrentWeatherData(lat, lon, AppId)



        call.enqueue(object : Callback<WeatherProperty> {
            override fun onResponse(
                call: Call<WeatherProperty>,
                response: Response<WeatherProperty>
            ) {

                if (response.code() == 200) {
                    val weatherResponse = response.body()!!
                    val stringBuilder = "Country: " +
                            weatherResponse.sys!!.country +
                            "\n" +
                            "Temperature: " +
                            weatherResponse.main!!.temp +
                            "\n" +
                            "Temperature(Min): " +
                            weatherResponse.main!!.temp_min +
                            "\n" +
                            "Temperature(Max): " +
                            weatherResponse.main!!.temp_max +
                            "\n" +
                            "Humidity: " +
                            weatherResponse.main!!.humidity +
                            "\n" +
                            "Pressure: " +
                            weatherResponse.main!!.pressure

                    //weatherData!!.text =stringBuilder.toString()

                    textView_weather.text = stringBuilder.toString()

                }


            }


            override fun onFailure(call: Call<WeatherProperty>, t: Throwable) {
                weatherData!!.text = t.message + "Error!!!!!"
            }
        })


    }

    //__________________

    /*
        override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
            inflater.inflate(R.menu.overflow_menu, menu)
            super.onCreateOptionsMenu(menu, inflater)
        }
    */
    //_______


    //_______


    companion object {

        var BaseUrl = "https://api.openweathermap.org/"
        var AppId = "50e61c814731ad7495b7ddef6ffc97ac"
        var lat = "53.669353"
        var lon = "23.813131"


        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WeatherFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WeatherFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



}