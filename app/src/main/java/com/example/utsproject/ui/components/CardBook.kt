import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.utsproject.ui.screen.books

@Composable
fun CardBook(idx: Int, modifier: Modifier = Modifier) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .height(200.dp)
            .width(150.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Bottom,
        ) {
            Text(
                text = "${books[idx].title}",
                modifier = modifier,
                style = TextStyle(
                    fontSize = 18.sp,
                    textAlign = TextAlign.Left,
                )
            )
            Text(
                text = "${books[idx].author}",
                modifier = modifier,
                style = TextStyle(
                    fontSize = 14.sp,
                    textAlign = TextAlign.Left,
                    color = Color(0xff546e7a)
                )
            )


        }
    }

}
