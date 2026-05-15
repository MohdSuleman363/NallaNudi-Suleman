package com.example.nallanudi.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nallanudi.data.model.Term
import com.example.nallanudi.viewmodel.TermViewModel

@Composable
fun MyListScreen(
    navController: NavController,
    termViewModel: TermViewModel,
    terms: List<Term>
) {

    val favoriteTerms = terms.filter {

        termViewModel.isFavorite(it)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F4FF))
            .padding(16.dp)
    ) {

        Text(
            text = "My List",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6A1B9A)
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (favoriteTerms.isEmpty()) {

            Box(
                modifier = Modifier.fillMaxSize()
            ) {

                Text(
                    text = "No saved words yet ⭐",
                    fontSize = 20.sp,
                    color = Color.Gray
                )
            }

        } else {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                items(favoriteTerms) { term ->

                    Card(

                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                                navController.navigate(
                                    "detail/${term.english}"
                                )
                            },

                        shape = RoundedCornerShape(22.dp),

                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),

                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(18.dp)
                        ) {

                            Text(
                                text = term.english,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )

                            Spacer(
                                modifier = Modifier.height(6.dp)
                            )

                            Text(
                                text = term.kannada,
                                fontSize = 18.sp,
                                color = Color.DarkGray
                            )

                            Spacer(
                                modifier = Modifier.height(10.dp)
                            )

                            Text(
                                text = term.definition,
                                fontSize = 15.sp,
                                color = Color.Gray
                            )
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