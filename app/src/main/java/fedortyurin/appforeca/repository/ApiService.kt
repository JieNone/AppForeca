package fedortyurin.appforeca.repository

import fedortyurin.appforeca.model.Forecast
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("json.end")
    suspend fun getForecast(): List<Forecast>

    companion object{
        private var apiService: ApiService? = null
        fun getInstance() : ApiService {
            if (apiService == null){
                apiService = Retrofit.Builder()
                    .baseUrl("json.start")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}