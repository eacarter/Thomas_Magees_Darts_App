package com.appsolution.mageescricketapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.appsolution.mageescricketapp.ui.theme.MageesCricketAppTheme

private lateinit var scoredPos: Array<Array<Int>>
private var playerCount : Int = 4
private var undoList = ArrayList<UndoObjects>()


@Composable
fun GamePage(numOfPlayers: Int) {

    generate(numOfPlayers)
    playerCount = numOfPlayers

    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        Row(

        ) {

            Column(
                modifier = Modifier
                    .weight(2f)
                    .background(Color.White)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black)
                ) {
                    for (i in 1..numOfPlayers) {
                        InitializePlayer(i)
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.End)
                        .background(
                            Color.Black
                        )
                        .padding(0.dp, 10.dp)
                ) {
//                    Button(
//                        modifier = Modifier
//                            .weight(1f)
//                            .padding(5.dp),
//                        shape = RoundedCornerShape(20.dp),
//                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
//
//                        onClick = { /*TODO*/ }) {
//
//                        Text(text = "Undo")
//                    }
//
//                    Button(
//                        modifier = Modifier
//                            .weight(1f)
//                            .padding(5.dp),
//                        shape = RoundedCornerShape(20.dp),
//                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
//
//                        onClick = { /*TODO*/ }) {
//
//                        Text(text = "New Game")
//                    }
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(.5f)
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .size(100.dp)
                        .fillMaxWidth(),
                ) {
                }

                for (i in 20 downTo 14) {
                    if (i != 14) {
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .padding(10.dp)
                                .border(
                                    width = 4.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(16.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = i.toString(),
                                color = Color.White,
                                modifier = Modifier
                                    .height(80.dp)
                                    .width(80.dp)
                                    .wrapContentHeight(),
                                textAlign = TextAlign.Center,
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .padding(10.dp)
                                .border(
                                    width = 4.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(16.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "BULL",
                                color = Color.White,
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
        Text(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            text = "Thomas Magee's Sporting House",
            color = Color.Yellow,
            fontSize = 30.sp,
        )

        Text(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            text = "Powered By Synchronous Enterprises",
            color = Color.White
        )
    }
}

@Composable
fun InitializePlayer(i: Int) {

    var buttonCountArray = remember { mutableListOf<Int>(0, 0, 0, 0, 0, 0, 0) }

    var points by remember { mutableStateOf(0) }

    Column() {
        Box(
            modifier = Modifier
                .size(100.dp)
                .padding(0.dp)
                .border(
                    width = 4.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Player $i",
                color = Color.White,
                modifier = Modifier
                    .height(80.dp)
                    .width(80.dp)
                    .padding(0.dp, 10.dp),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Score: $points",
                color = Color.White,
                modifier = Modifier
                    .height(80.dp)
                    .width(80.dp)
                    .wrapContentHeight()
                    .padding(0.dp, 10.dp),
                textAlign = TextAlign.Center
            )
        }
        for (j in 1..7) {

            var buttonText: Int by remember { mutableStateOf(0) }

            Button(
                modifier = Modifier
                    .size(100.dp)
                    .padding(5.dp)
                    .align(Alignment.CenterHorizontally)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onDoubleTap = {
                                if (buttonCountArray[j - 1] > 0 && buttonText > 0) {
                                    buttonCountArray[j - 1] -= 1
                                    if (buttonCountArray[j - 1] < 3) {
                                        scoredPos[i - 1][j - 1] = 0
                                    } else {
                                        when (j) {
                                            1 -> points -= pointValidation(1, 20)
                                            2 -> points -= pointValidation(2, 19)
                                            3 -> points -= pointValidation(3, 18)
                                            4 -> points -= pointValidation(4, 17)
                                            5 -> points -= pointValidation(5, 16)
                                            6 -> points -= pointValidation(6, 15)
                                            7 -> points -= pointValidation(7, 25)
                                        }
                                    }
                                }
                            }
                        )
                    },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                onClick = {
                    buttonText += 1
                    buttonCountArray[j - 1] += 1

                    if (buttonCountArray[j - 1] > 3) {

                        scoredPos[i-1][j-1] = 1

                        when (j) {
                            1 -> points += pointValidation(1, 20)
                            2 -> points += pointValidation(2, 19)
                            3 -> points += pointValidation(3, 18)
                            4 -> points += pointValidation(4, 17)
                            5 -> points += pointValidation(5, 16)
                            6 -> points += pointValidation(6, 15)
                            7 -> points += pointValidation(7, 25)
                        }
                    }

                    undoList.add(UndoObjects(
                        i,
                        j,
                        buttonCountArray[j-1],
                        points,
                        buttonText
                    ))
                },

                ) {
                Image(
                    painter = painterResource(
                        id = when (buttonText) {
                            0 -> R.drawable.ic_white_space
                            1 -> R.drawable.ic_slash
                            2 -> R.drawable.ic_cross
                            3 -> R.drawable.ic_circle
                            else -> R.drawable.ic_circle
                        }
                    ),
                    contentDescription = "Thomas Magee's Logo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                )
            }
        }
    }
}

private fun generate(players: Int) {
    scoredPos = Array(players) {
        Array(7) {
            0
        }
    }
}

private fun pointValidation(j : Int, points: Int): Int {

    var count = 0

    for ( i in scoredPos){
        if(i[j-1] == 1){
            count += 1
        }
    }

    if(count > playerCount - 1){
        return 0
    }
    else{
        return points
    }
}
