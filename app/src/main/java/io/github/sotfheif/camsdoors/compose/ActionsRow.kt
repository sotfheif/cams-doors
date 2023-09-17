package io.github.sotfheif.camsdoors.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.sotfheif.camsdoors.R

@Composable
fun ActionsRow(
    actionIconSize: Dp,
    //onDelete: () -> Unit,
    onEdit: () -> Unit,
    onFavorite: () -> Unit,
) {
    Row(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        /*
        IconButton(
            modifier = Modifier.size(actionIconSize),
            onClick = onDelete,
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bin),
                    contentDescription = null,
                )
            }
        )
         */
        IconButton(
            modifier = Modifier.size(actionIconSize),
            onClick = onEdit,
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.edit),
                    contentDescription = null,
                )
            },
        )
        IconButton(
            modifier = Modifier.size(actionIconSize),
            onClick = onFavorite,
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.circled_star),
                    contentDescription = null,
                )
            }
        )
    }
}