package com.example.nallanudi.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.example.nallanudi.data.model.Term

@Composable
fun MyListScreen(
    favoriteTerms: List<Term>,
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "My List",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (favoriteTerms.isEmpty()) {

            Text("No saved terms")

        } else {

            LazyColumn {

                items(favoriteTerms) { term ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                            .clickable {

                                navController.navigate(
                                    "detail/${term.english}/${term.kannada}/${term.definition}/${term.explanation}/${term.example}"
                                )
                            }
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = term.english,
                                style = MaterialTheme.typography.titleLarge
                            )

                            Text(
                                text = term.kannada
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = term.definition
                            )
                        }
                    }
                }
            }
        }
    }
}