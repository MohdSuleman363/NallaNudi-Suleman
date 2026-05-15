package com.example.nallanudi.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nallanudi.data.model.Term
import com.example.nallanudi.viewmodel.TermViewModel
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    termViewModel: TermViewModel,
    terms: List<Term>
) {

    var searchText by remember {
        mutableStateOf("")
    }

    var selectedCategory by remember {
        mutableStateOf("All")
    }

    val filteredTerms = terms.filter {

        val matchesSearch =

            it.english.contains(searchText, true) ||
                    it.kannada.contains(searchText, true)

        val matchesCategory =

            selectedCategory == "All" ||
                    it.subject.equals(selectedCategory, true)

        matchesSearch && matchesCategory
    }

    val currentDay = remember {
        LocalDate.now().dayOfYear
    }

    val wordOfTheDay = remember(currentDay) {

        terms[currentDay % terms.size]
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F4FF))
            .padding(16.dp)
    ) {

        Text(
            text = "Nalla-Nudi",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6A1B9A)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "The beauty of Language",
            fontSize = 18.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFEADCF8)
            ),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "Word of the Day",
                    color = Color(0xFF6A1B9A),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = wordOfTheDay.english,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(
                    text = wordOfTheDay.kannada,
                    fontSize = 22.sp,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = wordOfTheDay.definition,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
            },
            placeholder = {
                Text("Search terms")
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            listOf(
                "All",
                "Science",
                "Math"
            ).forEach { category ->

                FilterChip(
                    selected = selectedCategory == category,

                    onClick = {
                        selectedCategory = category
                    },

                    label = {
                        Text(category)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (filteredTerms.isEmpty()) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "No terms found",
                    fontSize = 20.sp,
                    color = Color.Gray
                )
            }

        } else {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(filteredTerms) { term ->

                    val isFavorite =
                        termViewModel.isFavorite(term)

                    AnimatedVisibility(
                        visible = true,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(
                                    elevation = 8.dp,
                                    shape = RoundedCornerShape(24.dp)
                                )
                                .clickable {

                                    navController.navigate(
                                        "detail/${term.english}"
                                    )
                                },

                            shape = RoundedCornerShape(24.dp),

                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            )
                        ) {

                            Column(
                                modifier = Modifier.padding(20.dp)
                            ) {

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement =
                                        Arrangement.SpaceBetween,
                                    verticalAlignment =
                                        Alignment.CenterVertically
                                ) {

                                    Column {

                                        Text(
                                            text = term.english,
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black
                                        )

                                        Spacer(
                                            modifier =
                                                Modifier.height(4.dp)
                                        )

                                        Text(
                                            text = term.kannada,
                                            fontSize = 18.sp,
                                            color = Color.DarkGray
                                        )
                                    }

                                    IconButton(
                                        onClick = {
                                            termViewModel
                                                .toggleFavorite(term)
                                        }
                                    ) {

                                        Icon(
                                            imageVector =
                                                Icons.Default.Star,

                                            contentDescription =
                                                "Favorite",

                                            tint =

                                                if (isFavorite)
                                                    Color(0xFFFFC107)

                                                else
                                                    Color.LightGray
                                        )
                                    }
                                }

                                Spacer(
                                    modifier =
                                        Modifier.height(12.dp)
                                )

                                Text(
                                    text = term.definition,
                                    fontSize = 16.sp,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }
}