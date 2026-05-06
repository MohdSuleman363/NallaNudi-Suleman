package com.example.nallanudi

import android.os.Bundle

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.padding

import androidx.compose.material3.*

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

import androidx.compose.ui.graphics.vector.ImageVector

import androidx.navigation.compose.*

import com.example.nallanudi.ui.components.BottomNavItem
import com.example.nallanudi.ui.screens.*

import com.example.nallanudi.viewmodel.TermViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()

            val database =
                remember {
                    com.example.nallanudi
                        .data.local
                        .AppDatabase
                        .getDatabase(this)
                }

            val termViewModel =
                remember {
                    TermViewModel(
                        database.termDao()
                    )
                }

            val items = listOf(

                BottomNavItem(
                    title = "Home",
                    route = "home",
                    icon = Icons.Default.Home
                ),

                BottomNavItem(
                    title = "My List",
                    route = "mylist",
                    icon = Icons.Default.Star
                ),

                BottomNavItem(
                    title = "About",
                    route = "about",
                    icon = Icons.Default.Info
                )
            )

            Scaffold(

                bottomBar = {

                    NavigationBar {

                        val currentRoute =
                            navController.currentBackStackEntryAsState()
                                .value
                                ?.destination
                                ?.route

                        items.forEach { item ->

                            NavigationBarItem(

                                selected =
                                    currentRoute == item.route,

                                onClick = {

                                    navController.navigate(item.route)
                                },

                                label = {

                                    Text(item.title)
                                },

                                icon = {

                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = item.title
                                    )
                                }
                            )
                        }
                    }
                }

            ) { paddingValues ->

                NavHost(

                    navController = navController,

                    startDestination = "home",

                    modifier = androidx.compose.ui.Modifier
                        .padding(paddingValues)
                ) {

                    composable("home") {

                        HomeScreen(
                            navController = navController,
                            viewModel = termViewModel
                        )
                    }

                    composable("mylist") {

                        val favorites by
                        termViewModel.favorites.collectAsState()

                        MyListScreen(
                            favoriteTerms = favorites,
                            navController = navController
                        )
                    }

                    composable("about") {

                        AboutScreen()
                    }

                    composable(
                        route =
                            "detail/{english}/{kannada}/{definition}/{explanation}/{example}"
                    ) { backStackEntry ->

                        DetailScreen(

                            english =
                                backStackEntry.arguments
                                    ?.getString("english")
                                    ?: "",

                            kannada =
                                backStackEntry.arguments
                                    ?.getString("kannada")
                                    ?: "",

                            definition =
                                backStackEntry.arguments
                                    ?.getString("definition")
                                    ?: "",

                            explanation =
                                backStackEntry.arguments
                                    ?.getString("explanation")
                                    ?: "",

                            example =
                                backStackEntry.arguments
                                    ?.getString("example")
                                    ?: ""
                        )
                    }
                }
            }
        }
    }
}