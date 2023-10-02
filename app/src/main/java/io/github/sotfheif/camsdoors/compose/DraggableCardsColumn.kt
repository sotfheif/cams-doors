package io.github.sotfheif.camsdoors.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.sotfheif.camsdoors.MainViewModel
import io.github.sotfheif.camsdoors.data.CardDb
import kotlinx.coroutines.ExperimentalCoroutinesApi

const val ACTION_ITEM_COUNT = 2
const val ACTION_ITEM_SIZE = 56
const val CARD_OFFSET = 1f * ACTION_ITEM_COUNT * ACTION_ITEM_SIZE

@ExperimentalCoroutinesApi
@Composable
fun DraggableCardsColumn(viewModel: MainViewModel, refreshing: Boolean, pageNum: Int) {
    val cards by viewModel.cards.observeAsState()
    val revealedCardIds by viewModel.revealedCardIdsList.observeAsState()

    LazyColumn(Modifier.fillMaxSize()) {
        if (!refreshing) {

            items(cards ?: listOf(), CardDb::id) { card ->
                Box(Modifier.fillMaxWidth()) {
                    ActionsRow(
                        actionIconSize = ACTION_ITEM_SIZE.dp,
                        //onDelete = {},
                        onEdit = {},
                        onFavorite = {}
                    )
                    //for advanced cases use DraggableCardComplex
                    DraggableCard(
                        card = card,
                        isRevealed = revealedCardIds?.contains(card.id) ?: false,
                        cardOffset = CARD_OFFSET.dp(),
                        onExpand = { viewModel.onItemExpanded(card.id) },
                        onCollapse = { viewModel.onItemCollapsed(card.id) },
                    )
                }
            }
        }
    }
}


