package com.example.utsproject.ui.screen

import CardBook
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import androidx.navigation.NavController
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.utsproject.Greeting
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.utsproject.ui.theme.UTSProjectTheme
import com.example.utsproject.data.model.Book
import com.example.utsproject.ui.viewmodel.BookViewModel
import androidx.compose.ui.unit.sp
//import com.example.utsproject.data.model.BookDB

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookListScreen(navController: NavController, modifier: Modifier = Modifier, viewModel: BookViewModel = hiltViewModel()) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    val books by viewModel.allBooks.observeAsState(initial = emptyList())

    Scaffold(
      modifier = Modifier.fillMaxSize(),

      floatingActionButton = {
          FloatingActionButton({
            navController.navigate("book_add")
          }) {
              Icon(Icons.Default.Add, contentDescription = "Add")
          }
      }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(30.dp,20.dp)
        ) {
            Text(
                text = "Book List",
                style = TextStyle(
                    fontSize = 40.sp,
                    textAlign = TextAlign.Left,
                )
            )
            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = modifier
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                for (idx in books.indices step 2) {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(screenHeight * 0.25f),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        CardBook(book = books[idx])

                        if (idx + 1 < books.size) {
                            CardBook(book = books[idx + 1])
                        }
                    }

                }
                Spacer(modifier = Modifier.padding(1.5.dp))

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookListScreenPreview() {
    UTSProjectTheme {
        val navController = rememberNavController()
        BookListScreen(navController)
    }
}
