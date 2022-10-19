package fedortyurin.appforeca.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fedortyurin.appforeca.model.Forecast
import fedortyurin.appforeca.repository.ApiService
import kotlinx.coroutines.launch
import java.lang.Exception

class ForecastViewModel: ViewModel() {
    var forecastListResponse: List<Forecast> by mutableStateOf(listOf())
    private var errorMessage: String by mutableStateOf("")



    fun getForecastList(){
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val forecastList = apiService.getForecast()
                forecastListResponse = forecastList

            }
            catch (e:Exception){
                errorMessage
            }
        }
    }
}

