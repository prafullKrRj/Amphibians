package com.example.amphibians.ui.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.model.AmphibianDetail

@Composable
fun HomeScreen(
    amphibianUiState: AmphibianUiState, modifier: Modifier = Modifier, retryAction: () -> Unit
) {
    when (amphibianUiState) {
        is AmphibianUiState.Loading -> LoadingScreen(modifier = modifier)
        is AmphibianUiState.Success -> DetailScreen(details = amphibianUiState.details, modifier = modifier)
        else -> ErrorScreen(retryAction = retryAction, modifier = modifier)
    }
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Loading Failed")
        Button(onClick = retryAction) {
            Text(text = "Retry")
        }
    }
}

@Composable
fun DetailScreen(details: List<AmphibianDetail>, modifier: Modifier) {
    LazyColumn (modifier = modifier
        .fillMaxWidth()
        .padding(16.dp)){
        items(details.size) {
            AmphibiansCard(Modifier.fillMaxWidth(), details[it])
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun AmphibiansCard(modifier: Modifier, detail: AmphibianDetail) {
    Card (
        shape = RoundedCornerShape(8.dp)
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            CardTitle(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), text = "${detail.name} + (${detail.type})")
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current).data(detail.imgSrc)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img),
                modifier = Modifier.clip(RoundedCornerShape(4.dp))
            )
            Text(text = detail.description, fontSize = 16.sp, modifier = Modifier.padding(16.dp))
        }

    }
}

@Composable
fun CardTitle(modifier: Modifier, text: String) {
    Text(text = text, fontSize = 20.sp, fontWeight = FontWeight.SemiBold, modifier = modifier)
}
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.loading_img),
        contentDescription = null,
        modifier = modifier
    )
}
