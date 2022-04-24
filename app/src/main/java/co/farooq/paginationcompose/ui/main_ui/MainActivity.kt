package co.farooq.paginationcompose.ui.main_ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.farooq.paginationcompose.ui.theme.PaginationComposeTheme

class MainActivity : ComponentActivity() {
    val viewModel by viewModels<PaginationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaginationComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    val state = viewModel.state
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(state.itemsRemoteList.size) { i ->
                            val item = state.itemsRemoteList[i]
                            if (i >= state.itemsRemoteList.size - 1 && !state.endReached && !state.isLoading) {
                                viewModel.loadNextItems()
                            }
                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp)
                            ) {
                                Text(text = item.name, fontSize = 16.sp, color = Color.Black, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = item.airline.maxOf { it -> it.name })
                                Spacer(modifier = Modifier.height(1.dp).background(Color.Gray))
                            }
                        }
                        item {
                            if (state.isLoading) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}