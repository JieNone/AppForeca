
package fedortyurin.appforeca.view

import android.R.string
import android.annotation.SuppressLint
import android.content.Context
import android.text.format.DateUtils.formatDateTime
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import fedortyurin.appforeca.AppForeca
import fedortyurin.appforeca.R
import fedortyurin.appforeca.model.Forecast
import fedortyurin.appforeca.ui.theme.*
import java.time.Month
import java.time.format.TextStyle
import java.util.*

//fun main(){
//    val numDateTime = "18-10, 02:35"
////    val charMonth = numDateTime.subSequence(3,5).toString()
////    val numMonth = charMonth.toInt()
////    val month = Month.of(numMonth).getDisplayName(TextStyle.FULL_STANDALONE, Locale.US)
////    val numDateTime = forecast.datetime
//    val charHour = numDateTime.subSequence(7,12).toString()
//    println(charHour)
//}

@Composable
fun GetHour(forecast: Forecast){
    val numDateTime = forecast.datetime // "18-10, 03:00"
    val charHour = numDateTime.subSequence(7,12).toString() // "03:00"
    return Text(
        textAlign = TextAlign.Center,
        text = charHour,
        fontWeight = FontWeight.Black,
        modifier = Modifier
    )
}
@Composable
fun GetMonth(forecast: Forecast){
        val numDateTime = forecast.datetime
    val charMonth = numDateTime.subSequence(3,5).toString()
    val numMonth = charMonth.toInt()
    val month = Month.of(numMonth).getDisplayName(TextStyle.FULL_STANDALONE, Locale.US)
    return Text(
        textAlign = TextAlign.Center,
        text = month,
        color = Tomato,
        fontWeight = FontWeight.Black,
        modifier = Modifier
    )
}@Composable
fun GetDay(forecast: Forecast){
    val numDateTime = forecast.datetime
    val charDay = numDateTime.subSequence(0,2).toString()
    return Text(
        textAlign = TextAlign.Center,
        text = charDay,
        fontSize = 40.sp,
        fontWeight = FontWeight.Black,
        modifier = Modifier
    )
}


@ExperimentalCoilApi
@Composable
fun ForecastItem(forecast: Forecast, navToDetail: (Forecast) -> Unit) {
    if (forecast.liga != ""){
    Card(
        elevation = 2.dp,
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(24.dp),
    ) {
        Surface(
            Modifier.clickable { navToDetail(forecast) }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(BackgroundColor)
                    .border(
                        width = 1.dp,
                        color = Color.LightGray.copy(alpha = 1f),
                    ),
            ) {
                HeaderRow(forecast = forecast)
                Row {
                    Column(
                        modifier = Modifier
                            .background(Color.White)
                            .width(((50 * 2 / 3) + 50).dp)
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {
                        GetDay(forecast)
                        GetMonth(forecast)
                        GetHour(forecast)
                    }
                    if (forecast.color == "Lime"){
                        LimeBody(forecast)
                    } else{ BlueBody(forecast)}
                }
            }
        }
    }
    Spacer(modifier = Modifier.padding(bottom = 16.dp))
    }
}
fun getDrawable(forecast: Forecast): Int {
    val context: Context = AppForeca.context
    val mFlagName = forecast.flag
    val resID: Int =
        context.resources.getIdentifier(mFlagName, "drawable", context.packageName)
    val resNull: Int = context.resources.getIdentifier("z_flag_sprite", "drawable", context.packageName)
    return if (resID == 0){
        resNull
    } else resID

}
@SuppressLint("UseCompatLoadingForDrawables")
    @Composable
    fun HeaderRow(forecast: Forecast) {
        if (forecast.color == "Lime") {
            LimeCard(forecast)
        } else {
            BlueCard(forecast)

        }

    }

@Composable
fun LimeCard(forecast: Forecast) {

    Row(
        modifier = Modifier
            .layoutId("cardHeader")
            .height((56).dp)
            .background(Lime),
    ) {
        Column(
            modifier = Modifier.fillMaxHeight()
        ){
            Image(
                painter = painterResource(id = getDrawable(forecast)),
                contentDescription = null,
                modifier = Modifier
                    .width(86.dp)
                    .height(56.dp)
            )
        }
        Text(
            textAlign = TextAlign.Center,
            text = forecast.liga,
            color = Color.White,
            maxLines = 2,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 0.dp, top = 8.dp)
        )
    }
}
@Composable
fun BlueCard(forecast: Forecast) {
    Row(
        modifier = Modifier
            .layoutId("cardHeader")
            .height((56).dp)
            .background(CoolBlue),
    ) {
        Column(
            modifier = Modifier.fillMaxHeight()
        ){
        Image(
            painter = painterResource(id = getDrawable(forecast)),
            contentDescription = null,
            modifier = Modifier
                .width(86.dp)
                .height(56.dp)
        )
        }
        Text(
            textAlign = TextAlign.Center,
            text = forecast.liga,
            color = Color.White,
            maxLines = 2,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 0.dp, top = 8.dp)
        )
    }
}

@Composable
fun BlueBody(forecast: Forecast) {
    Column(
        modifier = Modifier
            .background(
                LightBlueCard
            )
            .fillMaxSize()
            .padding(start = 8.dp),
    ) {
        Row {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Forecast: ${forecast.prognoz}",
                    fontWeight = FontWeight.Black,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 8.dp, top = 8.dp)
                )
                Image(
                    painter = rememberImagePainter(data = R.drawable.ic_arrow_btn,
                        builder = {
                            placeholder(R.drawable.ic_arrow_btn)

                        }),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .height(18.dp)
                        .width(18.dp)
                        .align(Alignment.CenterEnd),
                )
            }
        }
        Row {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(end = 24.dp),
                contentAlignment = Alignment.CenterStart
            ) {

                Text(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    text = forecast.team1
                )
                Text(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.CenterEnd),
                    text = forecast.score1
                )
            }
        }

        Row {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(end = 24.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    text = forecast.team2
                )
                Text(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    text = forecast.score2,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }

        }

    }

}
@Composable
fun LimeBody(forecast: Forecast) {
    Column(
        modifier = Modifier
            .background(
                LightLimeCard
            )
            .fillMaxSize()
            .padding(start = 8.dp),
    ) {
        Row {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Forecast: ${forecast.prognoz}",
                    fontWeight = FontWeight.Black,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 8.dp, top = 8.dp)
                )
                Image(
                    painter = rememberImagePainter(data = R.drawable.ic_arrow_btn,
                        builder = {
                            placeholder(R.drawable.ic_arrow_btn)

                        }),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .height(18.dp)
                        .width(18.dp)
                        .align(Alignment.CenterEnd),
                )
            }
        }
        Row {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(end = 24.dp),
                contentAlignment = Alignment.CenterStart
            ) {

                Text(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    text = forecast.team1
                )
                Text(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.CenterEnd),
                    text = forecast.score1
                )
            }
        }

        Row {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(end = 24.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    text = forecast.team2
                )
                Text(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    text = forecast.score2,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }

        }

    }

}