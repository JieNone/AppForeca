package fedortyurin.appforeca

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fedortyurin.appforeca.model.Forecast
import fedortyurin.appforeca.ui.theme.ForecaAppTheme
import fedortyurin.appforeca.view.DetailPage

class ForecastDetail : AppCompatActivity() {

     private val forecast: Forecast by lazy {

        intent?.getSerializableExtra(FORECAST_ID) as Forecast
        intent?.getSerializableExtra(FORECAST_TIME) as Forecast
        intent?.getSerializableExtra(FORECAST_TEAM1) as Forecast
        intent?.getSerializableExtra(FORECAST_TEAM2) as Forecast
        intent?.getSerializableExtra(FORECAST_COEF1) as Forecast
        intent?.getSerializableExtra(FORECAST_COEF2) as Forecast
        intent?.getSerializableExtra(FORECAST_COEF3) as Forecast
        intent?.getSerializableExtra(FORECAST_COLOR) as Forecast
        intent?.getSerializableExtra(FORECAST_FLAG) as Forecast
        intent?.getSerializableExtra(FORECAST_LIGA) as Forecast
        intent?.getSerializableExtra(FORECAST_PROGNOZ) as Forecast
        intent?.getSerializableExtra(FORECAST_RESULT) as Forecast
        intent?.getSerializableExtra(FORECAST_SCORE1) as Forecast
        intent?.getSerializableExtra(FORECAST_SCORE2) as Forecast
        intent?.getSerializableExtra(FORECAST_VERO1) as Forecast
        intent?.getSerializableExtra(FORECAST_VERO2) as Forecast
        intent?.getSerializableExtra(FORECAST_VERO3) as Forecast
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            ForecaAppTheme {
                DetailPage(forecast = forecast)
                
            }
        }
    }

    companion object{

        private const val FORECAST_ID = "forecast_id"
        private const val FORECAST_TIME = "forecast_datetime"
        private const val FORECAST_TEAM1 = "forecast_team1"
        private const val FORECAST_TEAM2 = "forecast_team2"
        private const val FORECAST_FLAG = "forecast_flag"
        private const val FORECAST_LIGA = "forecast_liga"
        private const val FORECAST_PROGNOZ = "forecast_prognoz"
        private const val FORECAST_VERO1 = "forecast_vero1"
        private const val FORECAST_VERO2 = "forecast_vero2"
        private const val FORECAST_VERO3 = "forecast_vero3"
        private const val FORECAST_COEF1 = "forecast_coef1"
        private const val FORECAST_COEF2 = "forecast_coef2"
        private const val FORECAST_COEF3 = "forecast_coef3"
        private const val FORECAST_RESULT = "forecast_result"
        private const val FORECAST_SCORE1 = "forecast_score1"
        private const val FORECAST_SCORE2 = "forecast_score3"
        private const val FORECAST_COLOR = "forecast_color"
        fun newIntent(context: Context, forecast: Forecast ) =
            Intent(context, ForecastDetail::class.java). apply {
                putExtra(FORECAST_ID, forecast)
                putExtra(FORECAST_RESULT, forecast)
                putExtra(FORECAST_VERO3, forecast)
                putExtra(FORECAST_VERO2, forecast)
                putExtra(FORECAST_VERO1, forecast)
                putExtra(FORECAST_TEAM2, forecast)
                putExtra(FORECAST_TEAM1, forecast)
                putExtra(FORECAST_TIME, forecast)
                putExtra(FORECAST_LIGA, forecast)
                putExtra(FORECAST_PROGNOZ, forecast)
                putExtra(FORECAST_FLAG, forecast)
                putExtra(FORECAST_COEF1, forecast)
                putExtra(FORECAST_COEF2, forecast)
                putExtra(FORECAST_COEF3, forecast)
                putExtra(FORECAST_SCORE1, forecast)
                putExtra(FORECAST_SCORE2, forecast)
                putExtra(FORECAST_COLOR, forecast)
            }
    }

}

