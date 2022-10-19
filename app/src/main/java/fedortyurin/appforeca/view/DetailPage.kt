package fedortyurin.appforeca.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import fedortyurin.appforeca.R
import fedortyurin.appforeca.model.Forecast
import fedortyurin.appforeca.ui.theme.BackgroundColor
import fedortyurin.appforeca.ui.theme.TransparentGray
import kotlin.reflect.typeOf


@Composable
fun DetailPage(forecast: Forecast){
    Surface(
        color = BackgroundColor,
    ) {
        Column {
            DetailHeader()
            BackButton()
            DetailMainPart(forecast = forecast)
            DetailProgress(forecast = forecast)
        }
    }
}


@Composable
fun DetailHeader(){
    Row(
        Modifier.padding(top = 16.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        Column(
            modifier = Modifier.
            fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = "Forecast",
                fontSize = 24.sp,
                color = Color.Yellow,
                fontWeight = FontWeight.Black,
            )
            Text(
                textAlign = TextAlign.Center,
                text = "TODAY",
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Black,
            )
        }

    }
}

@Composable
fun DetailMainPart(forecast: Forecast){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Row(
            modifier = Modifier.padding(bottom = 32.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)


        ) {
            Image(
                painter = painterResource(id = getDrawable(forecast)),
                contentDescription = null,
                modifier = Modifier
                    .size(56.dp)
            )
            Text(
                text = forecast.liga,
                fontSize = 16.sp,
                color = Color.White,
            )

        }

        Row(
            Modifier.padding(bottom = 16.dp)
        ) {
            Text(
                color = Color.White,
                text = forecast.team1,
                modifier = Modifier.padding(end = 24.dp, start = 8.dp)
            )
            Row(
                modifier = Modifier.padding(end = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    color = Color.Yellow,
                    text = forecast.score1,
                    fontWeight = FontWeight.Black
                )
                Text(
                    color = Color.Yellow,
                    text = ":",
                    fontWeight = FontWeight.Black
                )
                Text(
                    color = Color.Yellow,
                    text = forecast.score2,
                    fontWeight = FontWeight.Black
                )
            }
            Text(
                color = Color.White,
                text = forecast.team2,
                modifier = Modifier.padding(end = 8.dp)
            )

        }
        Row(
            Modifier.padding(bottom = 24.dp)
        ) {
            Text(
                text= forecast.datetime,
                color = Color.White,
                fontSize = 16.sp
            )

        }
        Divider(color = TransparentGray, thickness = 1.dp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp))
        Row {
                Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){

                Text(
                    text = "Forecast ${forecast.prognoz}",
                    color = Color.Yellow,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black
                )
            }
        }
        Divider(color = TransparentGray, thickness = 1.dp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp))
    }
}

@Composable
fun DetailProgress(forecast: Forecast){
    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 8.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(40.dp),
        ){
            Column() {
                Forc("1", 0.1f)
                Coef(forecast.coef1, 0.1f,((forecast.vero1.replace("%", "").toFloat())/100))

            }
            Column(
            ) {
                Forc("X", 0.15f)
                Coef(forecast.coef2, 0.15f, ((forecast.vero2.replace("%", "").toFloat())/100))

            }
            Column(
            ) {
                Forc("2", 0.16f)
                Coef(forecast.coef3, 0.15f, ((forecast.vero3.replace("%", "").toFloat())/100))

            }
        }

    }
}

@Composable
fun Forc(num: String, frac: Float){
    Text(text = num,color = Color.White, textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(frac))
}
@Composable
fun Coef(coef: String, frac: Float, vero: Float){
        CircularProgressIndicator(progress = vero , color = Color.Yellow)
        Text(text = coef,color = Color.White, modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth(frac), textAlign = TextAlign.Center)
}
@OptIn(ExperimentalCoilApi::class)
@Composable
fun BackButton() {
    Box(
        Modifier.padding(top = 16.dp),
    ){
    Row(
        modifier = Modifier
            .padding(start =16.dp,bottom = 32.dp)
    ){
            Image(
                painter = rememberImagePainter(data = R.drawable.arrow_back,
                    builder = {
                        placeholder(R.drawable.arrow_back)
                    }),
                contentDescription = null,
                modifier = Modifier.width(24.dp)
            )
            Text(
                textAlign = TextAlign.Center,
                text = "Back",
                color = Color.White,
                fontSize = 16.sp
            )
    }
    }
}

@Composable
@Preview
fun PreviewDetail(){
//    DetailPage()
}

