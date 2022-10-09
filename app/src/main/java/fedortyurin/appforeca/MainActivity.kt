package fedortyurin.appforeca

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
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
import java.time.OffsetDateTime
import java.time.ZoneOffset


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ForecaAppTheme {
                val forecastViewModel by viewModels<ForecastViewModel>()
                ForecastList(forecastList = forecastViewModel.forecastListResponse){
                    startActivity(ForecastDetail.newIntent(this, it))
                }
                forecastViewModel.getForecastList()

            }
        }
    }
}


@OptIn(ExperimentalCoilApi::class)
@Composable
 fun ForecastList(forecastList: List<Forecast>, navToDetail: (Forecast) -> Unit){
    val odtNow = OffsetDateTime.now(ZoneOffset.UTC)
    LazyColumn{
        itemsIndexed(items = forecastList.take(15)){
                _, item ->
            Card(Modifier.clickable(onClick = {
            })) {
                ForecastItem(forecast = item, navToDetail)
            }
        }

    }
}