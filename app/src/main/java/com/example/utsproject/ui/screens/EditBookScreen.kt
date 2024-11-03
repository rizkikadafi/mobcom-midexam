package com.example.utsproject.ui.screen

import android.service.carrier.MessagePdu
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController
import androidx.navigation.compose.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.utsproject.Greeting
import kotlinx.coroutines.launch
import com.example.utsproject.data.model.Book
import com.example.utsproject.data.model.Genre
import com.example.utsproject.ui.theme.UTSProjectTheme
import com.example.utsproject.ui.viewmodel.BookViewModel


@Composable
fun EditBookScreen(
    navController: NavController,
    bookId: Int,
    modifier: Modifier = Modifier,
    viewModel: BookViewModel = hiltViewModel()
) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var publicationYear by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf(Genre.ROMANCE) }
    var summary by remember { mutableStateOf("") }

    val book by viewModel.getBookById(bookId).observeAsState()

    LaunchedEffect(book) {
        book?.let {
            title = it.title
            author = it.author
            publicationYear = it.publicationYear.toString()
            genre = it.genre ?: Genre.ROMANCE
            summary = it.summary ?: ""
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp,80.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text= "Edit Book",
            style = TextStyle(
                fontSize = 40.sp,
            )
        )
        Spacer(modifier = Modifier.height(30.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") }
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = author,
            onValueChange = { author = it },
            label = { Text("Author") }
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = publicationYear,
            onValueChange = { publicationYear = it },
            label = { Text("Publication Year") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        DropdownMenuEditGenreSelector(selectedGenre = genre, onGenreSelected = { genre = it })

        TextField(
            modifier = Modifier.height(150.dp).fillMaxWidth(),
            value = summary,
            onValueChange = { summary = it },
            label = { Text("Summary") },
        )

        Button(
            onClick = {
              if (title.isNotBlank() && author.isNotBlank() && publicationYear.isNotBlank()) {
                  val publicationYearInt = publicationYear.toIntOrNull() ?: 0 // Konversi publicationYear ke Int, gunakan 0 jika null

                  val updatedBook = Book(
                      id = bookId,
                      title = title,
                      author = author,
                      publicationYear = publicationYearInt,
                      genre = genre,
                      summary = summary
                  )

                  viewModel.updateBook(updatedBook)

                  navController.navigate("book_list") {
                      popUpTo("book_list") { inclusive = true }
                  }
              }
            },
            modifier = Modifier.align(Alignment.End).fillMaxWidth()
        ) {
            Text("Edit Book")
        }
        OutlinedButton(
            onClick = { navController.navigate("book_list") },
            modifier = Modifier.align(Alignment.End).fillMaxWidth()
        ) {
            Text("Back")
        }
    }

}

@Composable
fun DropdownMenuEditGenreSelector(
    selectedGenre: Genre,
    onGenreSelected: (Genre) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .background(Color(0xffe2e2ec))
            .fillMaxWidth()
            .border(1.dp,Color(0xffadacb4))
    ) {
        Text(
            text = selectedGenre.name,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) { expanded = true }
                .padding(16.dp)
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth(0.85f)
        ) {
            Genre.values().forEach { genre ->
                DropdownMenuItem(
                    text = { Text(text = genre.name) },
                    onClick = {
                        onGenreSelected(genre)
                        expanded = false
                    }
                )
            }
        }
    }
}

// @Preview(showBackground = true)
// @Composable
// fun EditBookScreenPreview() {
//     UTSProjectTheme {
//         val navController = rememberNavController()
//         EditBookScreen(navController)
//     }
// }

