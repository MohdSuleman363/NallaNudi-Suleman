package com.example.nallanudi.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.*

import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController

import com.example.nallanudi.data.model.Term
import com.example.nallanudi.viewmodel.TermViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: TermViewModel
) {

    val query by viewModel.query.collectAsState()

    val filteredTerms by
    viewModel.filteredTerms.collectAsState(
        initial = emptyList()
    )

    val selectedSubject by
    viewModel.selectedSubject.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Nalla-Nudi",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(

            value = query,

            onValueChange = {
                viewModel.updateQuery(it)
            },

            label = {
                Text("Search terms")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row {

            listOf(
                "All",
                "Science",
                "Math"
            ).forEach { subject ->

                FilterChip(

                    selected =
                        selectedSubject == subject,

                    onClick = {
                        viewModel.updateSubject(subject)
                    },

                    label = {
                        Text(subject)
                    },

                    modifier =
                        Modifier.padding(end = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (filteredTerms.isEmpty()) {

            Text("No results found")

        } else {

            LazyColumn {

                items(filteredTerms) { term ->

                    TermCard(
                        term = term,
                        navController = navController,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun TermCard(

    term: Term,

    navController: NavController,

    viewModel: TermViewModel
) {

    val isFavorite =
        viewModel
            .favorites
            .collectAsState()
            .value
            .contains(term)

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .clickable {

                navController.navigate(
                    "detail/" +
                            "${term.english}/" +
                            "${term.kannada}/" +
                            "${term.definition}/" +
                            "${term.explanation}/" +
                            "${term.example}"
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

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = term.kannada
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = term.definition
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(

                onClick = {
                    viewModel.toggleFavorite(term)
                }

            ) {

                Text(
                    if (isFavorite)
                        "Remove"
                    else
                        "Save"
                )
            }
        }
    }
}