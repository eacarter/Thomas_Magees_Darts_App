package com.appsolution.mageescricketapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

    @Composable
    fun IntialPage(){

        var selected by remember { mutableStateOf("") }
        var expanded by remember { mutableStateOf(false) }
        val numOfPlayers = listOf(1,2,3,4)
        var dropDownWidth by remember { mutableStateOf(Size.Zero) }

        Column(modifier = Modifier
            .background(Color.Black)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Thomas Magees Logo",
                modifier = Modifier.padding(18.dp)
            )
            Text(
                text = "Thomas Magees Official Cricket Scoreboard",
                color = Color.White,
                modifier = Modifier.padding(18.dp)
            )
            OutlinedTextField(
                value = selected,
                onValueChange = {selected = it},
                label = {
                    Text(
                        text = "Number of Players",
                        color = Color.White,

                        )
                },
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
                    .padding(18.dp)
                    .onGloballyPositioned { coordinates ->
                        //This value is used to assign to the DropDown the same width
                        dropDownWidth = coordinates.size.toSize()
                    },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White
                ),
                textStyle = TextStyle(color = Color.White),
                trailingIcon = {
                    Icon(Icons.Filled.ArrowDropDown,"contentDescription", Modifier.clickable { expanded = !expanded }, tint = Color.White)
                }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                },
                modifier = Modifier
                    .width(with(LocalDensity.current){dropDownWidth.width.toDp()})
            ) {
                numOfPlayers.forEach{
                        label -> DropdownMenuItem(onClick= {selected = label.toString() }) {
                    Text(text = label.toString())
                }
                }
            }
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(18.dp)
            ) {
                Text(text = "Start Game")
            }

        }
    }
