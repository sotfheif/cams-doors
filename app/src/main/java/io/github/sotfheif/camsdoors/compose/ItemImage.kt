package io.github.sotfheif.camsdoors.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import io.github.sotfheif.camsdoors.R

@Composable
fun ItemImage(url: String = "") {
    Box {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.test_img_2),
            contentDescription = stringResource(R.string.item_image_desc),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth(),
            //.size(40.dp)
        )
        PlayBtnIcon()
        FavoritesIcon()
    }
}