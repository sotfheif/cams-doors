package io.github.sotfheif.camsdoors.compose

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import io.github.sotfheif.camsdoors.ui.theme.Grey10
import io.github.sotfheif.camsdoors.ui.theme.LightGrey

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CenterAlignedTopAppBar() {
    androidx.compose.material3.CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = LightGrey,
            titleContentColor = Grey10,
        ),
        title = {
            Text(
                "Мой дом",
                maxLines = 1,
            )
        },
    )
}