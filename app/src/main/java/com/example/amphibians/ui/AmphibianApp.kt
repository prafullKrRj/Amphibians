package com.example.amphibians.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibians.AmphibiansApplication
import com.example.amphibians.ui.Screens.AmphibianViewModel
import com.example.amphibians.ui.Screens.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansApp() {
    Scaffold (
        topBar = {
            AmphibiansAppBar()
        }
    ){
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ){
            val amphibiansViewModel: AmphibianViewModel = viewModel(factory = AmphibianViewModel.Factory)
            HomeScreen(
                amphibianUiState = amphibiansViewModel.amphibianUiState,
                modifier = Modifier.fillMaxSize(),
                retryAction = amphibiansViewModel::getDetails
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansAppBar() {
    TopAppBar(title = {
        Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            Text(
                text = "Amphibians",
                fontSize = 32.sp
            )
        }
    })
}