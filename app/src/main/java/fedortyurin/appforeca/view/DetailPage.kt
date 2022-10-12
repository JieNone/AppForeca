package fedortyurin.appforeca.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import fedortyurin.appforeca.R
import fedortyurin.appforeca.ui.theme.BackgroundColor
import fedortyurin.appforeca.ui.theme.CoolBlue
import fedortyurin.appforeca.ui.theme.TransparentGray


@Composable
fun DetailPage(){
    Surface(
        color = BackgroundColor,
    ) {
        Column {
            DetailHeader()
            BackButton()
            DetailMainPart()
        }
    }
}


@Composable
fun DetailHeader(){
    Row(
        Modifier.padding(top = 24.dp),
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
fun DetailMainPart(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Row(
            modifier = Modifier.padding(bottom = 32.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)


        ) {
            Text(
                text = "flag",
                fontWeight = FontWeight.Black,
                fontSize = 16.sp,
                color = Color.White,
            )
            Text(
                text = "liga",
                fontSize = 16.sp,
                color = Color.White,
            )

        }

        Row(
            Modifier.padding(bottom = 16.dp)
        ) {
            Text(
                color = Color.White,
                text = "team1",
                modifier = Modifier.padding(end = 80.dp)
            )
            Row(
                modifier = Modifier.padding(end = 80.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(

                    color = Color.Yellow,
                    text = "score1",
                    fontWeight = FontWeight.Black
                )
                Text(
                    color = Color.Yellow,
                    text = ":",
                    fontWeight = FontWeight.Black
                )
                Text(
                    color = Color.Yellow,
                    text = "score2",
                    fontWeight = FontWeight.Black
                )
            }
            Text(
                color = Color.White,
                text = "team2"
            )

        }
        Row(
            Modifier.padding(bottom = 32.dp)
        ) {
            Text(
                text= "datetime",
                color = Color.White,
                fontSize = 8.sp
            )

        }
        Divider(color = TransparentGray, thickness = 1.dp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp))
        Row(){
                Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){

                Text(
                    text = "Forecast {forecast.prognoz}",
                    color = Color.Yellow,
                    fontSize = 16.sp,
                )
            }
        }
        Divider(color = TransparentGray, thickness = 1.dp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp))
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun BackButton() {
    Box(
        Modifier.padding(top = 24.dp),
    ){
    Row(
        modifier = Modifier
            .padding(start = 16.dp)
            .fillMaxWidth(),
    ){
            Image(
                painter = rememberImagePainter(data = R.drawable.arrow_back,
                    builder = {
                        placeholder(R.drawable.arrow_back)
                    }),
                contentDescription = null,
            )
            Text(
                text = "Back",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 16.sp
            )
    }
    }

}



@Composable
@Preview
fun PreviewDetail(){
    DetailPage()
}