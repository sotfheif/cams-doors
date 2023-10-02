package io.github.sotfheif.camsdoors.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.github.sotfheif.camsdoors.R

@Composable
fun LockIcon(isLocked: Boolean = false) {
    val res = if (isLocked) R.drawable.lock_on else R.drawable.lock_off
    Image(
        painter = painterResource(res),
        contentDescription = null,
        modifier = Modifier.size(45.dp)
            .padding(horizontal = 10.dp)
    )
}

@Composable
fun BoxScope.PlayBtnIcon() {
    Image(
        painter = painterResource(R.drawable.play_button),
        contentDescription = null,
        modifier = Modifier.align(Alignment.Center)
            .size(70.dp),
    )
}

@Composable
fun BoxScope.FavoritesIcon() {
    Image(
        painter = painterResource(R.drawable.filled_star),
        contentDescription = null,
        modifier = Modifier.align(Alignment.TopEnd)
            .padding(4.dp)
            .size(28.dp),
    )
}

@Composable
fun BoxScope.RecIcon() {
    Image(
        painter = painterResource(R.drawable.rec),
        contentDescription = null,
        modifier = Modifier.align(Alignment.TopStart)
            .padding(4.dp)
            .size(28.dp),
    )
}