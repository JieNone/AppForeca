
package fedortyurin.appforeca.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import fedortyurin.appforeca.ForecastDetail
import fedortyurin.appforeca.R
import fedortyurin.appforeca.model.Forecast
import fedortyurin.appforeca.ui.theme.BackgroundColor
import fedortyurin.appforeca.ui.theme.CoolBlue
import fedortyurin.appforeca.ui.theme.Lime
import fedortyurin.appforeca.ui.theme.Tomato
import java.time.OffsetDateTime
import java.time.ZoneOffset

@ExperimentalCoilApi
@Composable
fun ForecastItem(forecast: Forecast, navToDetail: (Forecast) -> Unit) {
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
                            .background(Color.White,)
                            .fillMaxHeight()
                    )
                    {
                        Box(
                            modifier = Modifier
                                .width(((50 * 2 / 3) + 50).dp),
                        ) {
                            Text(
                                textAlign = TextAlign.Center,
                                text = "2",
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Black,
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )
                        }
                        Box(

                            modifier = Modifier
                                .width(((50 * 2 / 3) + 50).dp),

                            ) {
                            Text(
                                textAlign = TextAlign.Center,
                                text = forecast.datetime,
                                color = Tomato,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .width(((50 * 2 / 3) + 50).dp),
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .background(
                                Color.LightGray,
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
            }
        }
    }
    Spacer(modifier = Modifier.padding(bottom = 16.dp))
}
@Composable
fun HeaderRow(forecast: Forecast) {
    if (forecast.color == "Lime"){
        Row(

            modifier = Modifier
                .layoutId("cardHeader")
                .height((48).dp)
                .background(Lime),
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = forecast.flag.uppercase(),
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier
                    .width(80.dp)
                    .padding(top = 8.dp)
            )
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
    } else{
        Row(

            modifier = Modifier
                .layoutId("cardHeader")
                .height((48).dp)
                .background(CoolBlue),
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = forecast.flag.uppercase(),
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier
                    .width(80.dp)
                    .padding(top = 8.dp)
            )
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

}

