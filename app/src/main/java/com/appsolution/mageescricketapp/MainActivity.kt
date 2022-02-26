package com.appsolution.mageescricketapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.media.MediaMetadataRetriever
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.appsolution.mageescricketapp.ui.theme.MageesCricketAppTheme
import org.intellij.lang.annotations.JdkConstants
import android.graphics.drawable.Drawable
import android.view.View


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MageesCricketAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    GamePage(4)
                }
            }
        }
    }
}

@Composable
fun GamePage(numOfPlayers : Int){

    Row(modifier = Modifier
        .background(Color.Black)
        .fillMaxSize()) {

        Column(
            modifier = Modifier
                .weight(2f)
                .background(White)
        ) {
            Row(horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Black)) {
                for (i in 1..numOfPlayers) {
                    InitializePlayer(i)
                }
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(.5f)
        ) {
            Box(
                modifier = Modifier
                    .background(Color.DarkGray)
                    .size(160.dp)
                    .fillMaxWidth(),
            )

            for (i in 20 downTo 14){
                if(i != 14) {
                    Box(modifier = Modifier
                        .size(120.dp)
                        .padding(10.dp)
                        .border(width = 4.dp, color = White, shape = RoundedCornerShape(16.dp)),
                        contentAlignment = Alignment.Center) {
                        Text(
                            text = i.toString(),
                            color = White,
                            modifier = Modifier
                                .height(80.dp)
                                .width(80.dp)
                                .wrapContentHeight(),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
                else{
                    Box(modifier = Modifier
                        .size(120.dp)
                        .padding(10.dp)
                        .border(width = 4.dp, color = White, shape = RoundedCornerShape(16.dp)),
                        contentAlignment = Alignment.Center) {
                        Text(
                            text = "BULL",
                            color = White,
                            modifier = Modifier
                                .height(80.dp)
                                .width(80.dp)
                                .wrapContentHeight(),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun InitializePlayer(i: Int) {

    var buttonCountArray = remember{mutableListOf<Int>(0,0,0,0,0,0,0)}

    var points by remember{ mutableStateOf(0)}

    val gamePos = Array(i){ Array(buttonCountArray.size) {0} }

    Column() {
        Box(
            modifier = Modifier
                .background(Color.White)
                .size(160.dp)
                .fillMaxWidth()
                .border(width = 4.dp, color = White, shape = RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Player $i",
                color = Black,
                modifier = Modifier
                    .height(80.dp)
                    .width(80.dp),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Score: $points",
                color = Black,
                modifier = Modifier
                    .height(80.dp)
                    .width(80.dp)
                    .wrapContentHeight(),
                textAlign = TextAlign.Center
            )
        }
        for(j in 1..7){

            var buttonText:Int by remember{ mutableStateOf(0)}

            Button(
                modifier = Modifier
                    .size(120.dp)
                    .padding(10.dp)
                    .align(CenterHorizontally),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = White),
                onClick = {
                    buttonText += 1
                    buttonCountArray[j - 1] += 1

                    if(buttonCountArray[j - 1] > 3) {
                        when(j){
                            1 -> points += 20
                            2 -> points += 19
                            3 -> points += 18
                            4 -> points += 17
                            5 -> points += 16
                            6 -> points += 15
                            7 -> points += 25
                        }
                    }


                }
            ) {
                Image(
                    painter = painterResource(
                        id = when (buttonText) {
                            0 -> R.drawable.ic_white_space
                            1 -> R.drawable.ic_slash
                            2 -> R.drawable.ic_cross
                            3 -> R.drawable.ic_circle
                            else -> R.drawable.ic_circle
                        }),
                        contentDescription = "Thomas Magee's Logo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.height(60.dp).width(60.dp)
                    )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MageesCricketAppTheme {
       GamePage(numOfPlayers = 4)
    }
}