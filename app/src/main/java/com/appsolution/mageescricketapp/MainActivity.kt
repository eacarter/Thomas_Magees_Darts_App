package com.appsolution.mageescricketapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.appsolution.mageescricketapp.ui.theme.MageesCricketAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MageesCricketAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    DartApp()
                }
            }
        }
    }

    @Composable
    fun DartApp() {
        val navController = rememberNavController()
        NavHost(navController, startDestination = "initial") {
            composable(route = "initial") {
                IntialPage(navController)
            }
            composable(route = "Game/{id}") { navBackStack ->

                val players = navBackStack.arguments?.getString("id")

                if (players != null) {
                    GamePage(players.toInt())
                }
            }
        }
    }
}