package io.github.sotfheif.camsdoors.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sotfheif.camsdoors.TEST_IMAGE_URL
import io.github.sotfheif.camsdoors.ui.theme.Grey20
import io.github.sotfheif.camsdoors.ui.theme.Grey50


@Composable
fun DoorItem() {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
            //.padding(10.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(7.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                ItemImage(TEST_IMAGE_URL)
                Box(
                    modifier = Modifier
                        .padding(7.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column(
                            //verticalArrangement = Arrangement.spacedBy(0.dp),
                        ) {
                            Text(
                                text = "doorIt",
                                color = Grey50,
                                //color = MaterialTheme.colorScheme.background,
                                modifier = Modifier
                            )
                            Text(
                                text = "doorIt",
                                color = Grey20,
                                fontSize = 14.sp,
                                //.modifier = Modifier
                                //.wrapContentSize(unbounded = true),
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                    ) {
                        LockIcon()
                    }
                }
            }
        }
    }
}