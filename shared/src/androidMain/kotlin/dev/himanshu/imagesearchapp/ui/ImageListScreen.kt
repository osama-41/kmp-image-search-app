package dev.himanshu.imagesearchapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.SubcomposeAsyncImage
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ImageListScreen(modifier: Modifier = Modifier) {

    val viewModel = koinViewModel <ImageViewModel>()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val query by viewModel.query.collectAsStateWithLifecycle()



    ImageListScreenContent(
        modifier = modifier,
        uiState = uiState,
        query = query,
        onQueryChanged = viewModel::updateQuery
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageListScreenContent(
    modifier: Modifier = Modifier,
    uiState: ImageUiState,
    query: String,
    onQueryChanged: (String) -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TextField(
                value = query,
                onValueChange = onQueryChanged,
                modifier = Modifier.fillMaxWidth(),
                )
        }
    ) { innerPadding ->
        when{
            uiState.isLoading -> {
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()
                }
            }

            uiState.error.isNotBlank()->{
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(uiState.error)
                }
            }

            uiState.data!=null -> {
                if (uiState.data.isNullOrEmpty()){
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        Text("No images found.")
                    }
                }else {
                    LazyColumn(modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                    ) {
                        items(uiState.data) { item ->

                            SubcomposeAsyncImage(
                                model = item.imageUrl,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.padding(8.dp)
                                    .fillMaxWidth()
                                    .height(280.dp)
                            )

                        }
                    }
                }
            }
        }
    }
}