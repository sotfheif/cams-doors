package io.github.sotfheif.camsdoors.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import io.github.sotfheif.camsdoors.TEST_IMAGE_URL2

@Composable
fun CamItem() {
    Surface(
        shape = RoundedCornerShape(16.dp),
        //color = MaterialTheme.colorScheme.surface
    ) {
        Column {
            ItemImage(TEST_IMAGE_URL2)

            Text(
                text = "camIt",
                //color = MaterialTheme.colorScheme.background
            )
        }
    }
}