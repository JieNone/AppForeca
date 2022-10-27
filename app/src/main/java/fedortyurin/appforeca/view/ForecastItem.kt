
package fedortyurin.appforeca.view

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import fedortyurin.appforeca.AppForeca
import fedortyurin.appforeca.R
import fedortyurin.appforeca.model.Forecast
import fedortyurin.appforeca.ui.theme.*
import java.time.LocalDateTime
import java.time.Month
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

fun getDate(pattern: String): String {
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return current.format(formatter)

}
fun currentHour(forecast: Forecast):String {
    val forecaHour = forecast.datetime
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return forecaHour.format(formatter)
}
fun getCurrentMonth(pattern: String): String {
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val formatted = current.format(formatter)
    val numMonth = formatted.toInt()
    return Month.of(numMonth).getDisplayName(TextStyle.FULL_STANDALONE, Locale.US)
}

@Composable
fun GetHour(forecast: Forecast, color: Color = Color.Black, fontWeight: FontWeight = FontWeight.Black){
    if(forecast.datetime.length < 11){
        return Text(
            textAlign = TextAlign.Center,
            text = currentHour(forecast),
            color = color,
            fontWeight = fontWeight,
            modifier = Modifier
        )
    }
    else{
        val numDateTime = forecast.datetime // "18-10, 03:00"
        val charHour = numDateTime.subSequence(7,12).toString() // "03:00"
    return Text(

        textAlign = TextAlign.Center,
        text = charHour,
        color = color,
        fontWeight = fontWeight,
        modifier = Modifier
    )
    }
}
@Composable
fun GetMonth(forecast: Forecast, color: Color = Tomato, fontWeight: FontWeight = FontWeight.Black ){
    if (forecast.datetime.length < 11){
        return Text(
            textAlign = TextAlign.Center,
            text = getCurrentMonth("MM"),
            color = color,
            fontWeight = fontWeight,
            modifier = Modifier
        )
    } else {
        val numDateTime = forecast.datetime
        val charMonth = numDateTime.subSequence(3,5).toString()
        val numMonth = charMonth.toInt()
        val month = Month.of(numMonth).getDisplayName(TextStyle.FULL_STANDALONE, Locale.US)
        return Text(
            textAlign = TextAlign.Center,
            text = month,
            color = color,
            fontWeight = fontWeight,
            modifier = Modifier
        )
    }
}
@Composable
fun GetDay(forecast: Forecast, color: Color = Color.Black, fontWeight: FontWeight = FontWeight.Black, fontSize: TextUnit = 40.sp){
    if (forecast.datetime.length > 11){
    val numDateTime = forecast.datetime
    val charDay = numDateTime.subSequence(0,2).toString()
    return Text(
        textAlign = TextAlign.Center,
        text = charDay,
        fontSize = fontSize,
        color = color,
        fontWeight = fontWeight,
        modifier = Modifier
    )
    } else {
        return Text(
            textAlign = TextAlign.Center,
            text = getDate("dd"),
            fontSize = fontSize,
            color = color,
            fontWeight = fontWeight,
            modifier = Modifier
        )
    }
}


@ExperimentalCoilApi
@Composable
fun ForecastItem(forecast: Forecast, navToDetail: (Forecast) -> Unit) {
    if (forecast.liga != ""){
        Spacer(modifier = Modifier.padding(bottom = 8.dp))
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.clickable { navToDetail(forecast) }.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.height(160.dp)
            ){
                Column(
                    modifier = Modifier
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
                                .width(80.dp)
                                .fillMaxHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        )
                        {
                            GetDay(forecast)
                            GetMonth(forecast)
                            GetHour(forecast)
                        }
                        if (forecast.color == "Lime") {
                            LimeBody(forecast)
                        } else {
                            BlueBody(forecast)
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(bottom = 8.dp))
    }
}
fun getFlag(forecast: Forecast): Int {
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
            .height((56).dp)
            .background(Lime),
    ) {
        Column(
            modifier = Modifier.fillMaxHeight()
        ){
            Image(
                painter = painterResource(id = getFlag(forecast)),
                contentDescription = null,
                modifier = Modifier
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
            .height((56).dp)
            .background(CoolBlue),
    ) {
        Column(
            modifier = Modifier.fillMaxHeight()
        ){
        Image(
            painter = painterResource(id = getFlag(forecast)),
            contentDescription = null,
            modifier = Modifier
        )
        }
        Text(
            textAlign = TextAlign.Center,
            text = forecast.liga,
            color = Color.White,
            maxLines = 2,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
    }
}

@OptIn(ExperimentalCoilApi::class)
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
                    text = forecast.team1,

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
@OptIn(ExperimentalCoilApi::class)
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