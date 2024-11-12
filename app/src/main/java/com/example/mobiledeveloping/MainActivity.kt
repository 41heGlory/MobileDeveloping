package com.example.mobiledeveloping

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.mobiledeveloping.data.WeatherModel
import com.example.mobiledeveloping.ui.theme.MobileDevelopingTheme
import com.example.mobiledeveloping.ui_components.DialogSearch
import com.example.mobiledeveloping.ui_components.MainScreen
import com.example.mobiledeveloping.ui_components.TabLayout
import com.example.myapplication.data.HourDto
import com.example.myapplication.data.WeatherDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "4d9910d65c9e40e389e164949241111"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileDevelopingTheme {
                val daysList = remember { mutableStateOf(listOf<WeatherModel>()) }
                val dialogState = remember { mutableStateOf(false) }
                val currentDay = remember { mutableStateOf(WeatherModel("", "", "0.0", "", "", "0.0", "0.0", emptyList())) }

                if (dialogState.value) {
                    DialogSearch(dialogState) { city ->
                        getData(city, this, daysList, currentDay)
                    }
                }

                // Инициализируем данные только один раз
                if (daysList.value.isEmpty()) {
                    getData("London", this, daysList, currentDay)
                }

                Image(
                    painter = painterResource(id = R.drawable.weather_bg),
                    contentDescription = "im1",
                    modifier = Modifier.fillMaxSize().alpha(0.5f),
                    contentScale = ContentScale.FillBounds
                )

                Column {
                    MainScreen(currentDay, onClickSync = {
                        getData("London", this@MainActivity, daysList, currentDay)
                    }, onClickSearch = {
                        dialogState.value = true
                    })
                    TabLayout(daysList, currentDay)
                }
            }
        }
    }
}

interface WeatherApi {
    @GET("forecast.json")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("key") apiKey: String = API_KEY,
        @Query("days") days: Int = 3,
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no",
        @Query("lang") lang: String = "ru"
    ): WeatherDto
}

private fun getData(
    city: String,
    context: Context,
    daysList: MutableState<List<WeatherModel>>,
    currentDay: MutableState<WeatherModel>
) {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.weatherapi.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: WeatherApi = retrofit.create(WeatherApi::class.java)

    CoroutineScope(Dispatchers.IO).launch {
        try {
            val weather = service.getWeather(city)

            // Обновляем список дней с погодой
            daysList.value = weather.forecast.forecastday.map { forecastDay ->
                WeatherModel(
                    city = weather.location.name,
                    time = forecastDay.date,
                    currentTemp = "", // Здесь можно оставить пустым, если нет текущей температуры
                    condition = forecastDay.day.condition.text,
                    icon = forecastDay.day.condition.icon,
                    maxTemp = "${forecastDay.day.maxTemp.toInt()}°C", // Используем maxTemp
                    minTemp = "${forecastDay.day.minTemp.toInt()}°C", // Используем minTemp
                    hours = forecastDay.hour // Убедитесь, что это List<HourDto>
                )
            }

            // Обновляем текущий день с погодой
            currentDay.value = WeatherModel(
                city = weather.location.name,
                time = weather.current.time,
                currentTemp = "${weather.current.currentTemp.toInt()}°C", // Преобразуем текущую температуру
                condition = weather.forecast.forecastday[0].day.condition.text,
                icon = weather.forecast.forecastday[0].day.condition.icon,
                maxTemp = "${weather.forecast.forecastday[0].day.maxTemp.toInt()}°C",
                minTemp = "${weather.forecast.forecastday[0].day.minTemp.toInt()}°C",
                hours = weather.forecast.forecastday[0].hour
            )
        } catch (e: Exception) {
            Log.e("WeatherAPI", "Error fetching weather data", e)
            // Здесь можно добавить код для отображения ошибки пользователю
        }
    }
}