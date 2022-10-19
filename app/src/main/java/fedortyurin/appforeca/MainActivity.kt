 package fedortyurin.appforeca

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi
import fedortyurin.appforeca.model.Forecast
import fedortyurin.appforeca.ui.theme.ForecaAppTheme
import fedortyurin.appforeca.view.ForecastItem
import fedortyurin.appforeca.viewModel.ForecastViewModel


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
        setContent   {
            ForecaAppTheme {
                val forecastViewModel by viewModels<ForecastViewModel>()
                ForecastList(forecastList = forecastViewModel.forecastListResponse){
                    startActivity(ForecastDetail.newIntent(context = this, forecast = it))
                }
                forecastViewModel.getForecastList()

            }
        }
    }
}
class AppForeca : Application() {
    companion object {
        private lateinit var instance: AppForeca
        val context: Context
            get() = instance
    }

    init {
        instance = this
    }
}


@OptIn(ExperimentalCoilApi::class)
@Composable
 fun ForecastList(forecastList: List<Forecast>, navToDetail: (Forecast) -> Unit){
    LazyColumn{
        itemsIndexed(items = forecastList){
                _, item ->
            Card(Modifier.clickable(onClick = {
            })) {
                ForecastItem(forecast = item, navToDetail)
            }
        }
    }
}
