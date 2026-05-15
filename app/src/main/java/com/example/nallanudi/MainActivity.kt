package com.example.nallanudi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.nallanudi.data.model.Term
import com.example.nallanudi.data.repository.TermRepository
import com.example.nallanudi.ui.screens.AboutScreen
import com.example.nallanudi.ui.screens.DetailScreen
import com.example.nallanudi.ui.screens.HomeScreen
import com.example.nallanudi.ui.screens.MyListScreen
import com.example.nallanudi.ui.theme.NallaNudiTheme
import com.example.nallanudi.viewmodel.TermViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            NallaNudiTheme {

                val navController =
                    rememberNavController()

                val termViewModel: TermViewModel =
                    viewModel()

                val terms =
                    remember {
                        TermRepository.getTerms()
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

                            val navBackStackEntry by
                            navController.currentBackStackEntryAsState()

                            val currentRoute =
                                navBackStackEntry
                                    ?.destination
                                    ?.route

                            items.forEach { item ->

                                NavigationBarItem(

                                    selected =
                                        currentRoute == item.route,

                                    onClick = {

                                        navController.navigate(
                                            item.route
                                        ) {

                                            popUpTo(
                                                navController.graph.startDestinationId
                                            ) {
                                                saveState = true
                                            }

                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },

                                    icon = {

                                        Icon(
                                            imageVector = item.icon,
                                            contentDescription = item.title
                                        )
                                    },

                                    label = {
                                        Text(item.title)
                                    }
                                )
                            }
                        }
                    }

                ) { innerPadding ->

                    NavHost(

                        navController = navController,

                        startDestination = "home",

                        modifier = Modifier.padding(innerPadding)
                    ) {

                        composable("home") {

                            HomeScreen(
                                navController = navController,
                                termViewModel = termViewModel,
                                terms = terms
                            )
                        }

                        composable("mylist") {

                            MyListScreen(
                                navController = navController,
                                termViewModel = termViewModel,
                                terms = terms
                            )
                        }

                        composable("about") {

                            AboutScreen()
                        }

                        composable(

                            route = "detail/{english}",

                            arguments = listOf(

                                navArgument("english") {
                                    type = NavType.StringType
                                }
                            )
                        ) { backStackEntry ->

                            val english =
                                backStackEntry.arguments
                                    ?.getString("english")

                            val term =
                                terms.find {
                                    it.english == english
                                }

                            if (term != null) {

                                DetailScreen(
                                    english = term.english,
                                    kannada = term.kannada,
                                    definition = term.definition,
                                    explanation = term.explanation,
                                    example = term.example
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

data class BottomNavItem(
    val title: String,
    val route: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)