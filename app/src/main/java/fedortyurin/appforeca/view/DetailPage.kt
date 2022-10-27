package fedortyurin.appforeca.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import fedortyurin.appforeca.MainActivity
import fedortyurin.appforeca.R
import fedortyurin.appforeca.model.Forecast
import fedortyurin.appforeca.ui.theme.BackgroundColor
import fedortyurin.appforeca.ui.theme.TransparentGray


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
                painter = painterResource(id = getFlag(forecast)),
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
            Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            Box(
                Modifier.width(160.dp),
            ){
                Text(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    text = forecast.team1,
                    modifier = Modifier.padding(start = 8.dp),
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(
                modifier = Modifier.padding(end = 24.dp),
            ){
                if (forecast.score1 == "" || forecast.score2 == ""){
                    val score1 = "0"
                    val score2 = "0"
                    Text(
                        color = Color.Yellow,
                        fontSize = 24.sp,
                        text = score1,
                        fontWeight = FontWeight.Black,

                        )
                    Text(
                        color = Color.Yellow,
                        fontSize = 24.sp,
                        text = ":",
                        fontWeight = FontWeight.Black
                    )
                    Text(
                        color = Color.Yellow,
                        fontSize = 24.sp,
                        text = score2,
                        fontWeight = FontWeight.Black
                    )
                }else{
                    Text(
                        color = Color.Yellow,
                        fontSize = 24.sp,
                        text = forecast.score1,
                        fontWeight = FontWeight.Black,

                        )
                    Text(
                        color = Color.Yellow,
                        fontSize = 24.sp,
                        text = ":",
                        fontWeight = FontWeight.Black
                    )
                    Text(
                        color = Color.Yellow,
                        fontSize = 24.sp,
                        text = forecast.score2,
                        fontWeight = FontWeight.Black
                    )

                }
            }
            Box(
                Modifier.width(160.dp),
            ) {
                Text(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    text = forecast.team2,
                    modifier = Modifier.padding(end = 8.dp),
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Row(
            Modifier.padding(top = 16.dp, bottom = 24.dp)
        ) {
            GetDay(forecast, color = Color.White, fontWeight = FontWeight.Normal, fontSize = 16.sp)
            Spacer(modifier = Modifier.padding(end = 4.dp))
            GetMonth(forecast, color = Color.White, fontWeight = FontWeight.Normal)
            Spacer(modifier = Modifier.padding(end = 4.dp))
            GetHour(forecast, color = Color.White, fontWeight = FontWeight.Normal)

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
                    fontWeight = FontWeight.Normal
                )
            }
        }
        Divider(color = TransparentGray, thickness = 1.dp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp))
    }
}

@Composable
fun DetailProgress(forecast: Forecast){
    Box(
            Modifier.padding(top = 8.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "1",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
                CustomCoef((forecast.vero1.replace("%", "").toFloat()/100), strokeWidth = 4.dp)
                Text(text = forecast.coef1, color = Color.White, modifier = Modifier.padding(top = 8.dp), textAlign = TextAlign.Center)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "X",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
                CustomCoef((forecast.vero2.replace("%", "").toFloat()/100), strokeWidth = 4.dp)
                Text(text = forecast.coef2, color = Color.White, modifier = Modifier.padding(top = 8.dp), textAlign = TextAlign.Center)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "2",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
                CustomCoef((forecast.vero3.replace("%", "").toFloat()/100), strokeWidth = 4.dp)
                Text(text = forecast.coef3, color = Color.White, modifier = Modifier.padding(top = 8.dp), textAlign = TextAlign.Center)
            }

        }
    }
}

@Composable
fun CustomCoef(vero: Float, strokeWidth: Dp = 8.dp){
    Column {
    Box(
        contentAlignment = Alignment.Center
    ){
        Canvas(modifier = Modifier.size(56.dp)){
            drawArc(
                color = Color.Yellow,
                startAngle = -90f,
                sweepAngle = 360f * vero,
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        val percent = (vero * 100).toInt().toString().plus("%")
        Text(text = percent,color = Color.White, modifier = Modifier
            .padding(top = 8.dp),
            textAlign = TextAlign.Center)
    }
    }
}



@OptIn(ExperimentalCoilApi::class)
@Composable
fun BackButton() {
    Box(
        Modifier.padding(top = 16.dp).clickable { },
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

