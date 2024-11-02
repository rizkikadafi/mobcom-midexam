package com.example.utsproject.ui.screen

import CardBook
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.utsproject.Greeting
import com.example.utsproject.ui.theme.UTSProjectTheme
import com.example.utsproject.data.model.Book
import androidx.compose.ui.unit.sp

val book1 = Book(
    id = 1,
    title = "The Great Gatsby",
    author = "F. Scott Fitzgerald",
    summary = "A novel set in the Roaring Twenties, exploring themes of wealth, society, and the American dream."
)

val book2 = Book(
    id = 2,
    title = "The Big Monas",
    author = "F. Scott Fitzgerald",
    summary = "A novel set in the Roaring Twenties, exploring themes of wealth, society, and the American dream."
)

val book3 = Book(
    id = 3,
    title = "The Eiffel",
    author = "F. Scott Fitzgerald",
    summary = "A novel set in the Roaring Twenties, exploring themes of wealth, society, and the American dream."
)

val books = arrayOf(book1,book2,book3)

@Composable
fun BookListScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        Text(
            text = "Book List",
            modifier = modifier,
            style = TextStyle(
                fontSize = 50.sp,
                textAlign = TextAlign.Left,
            )
        )
        for (idx in books.indices step 2) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .height(170.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CardBook(idx)

                if (idx + 1 < books.size) {
                    CardBook(idx)
                }
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun BookListScreenPreview() {
    UTSProjectTheme {
        BookListScreen()
    }
}