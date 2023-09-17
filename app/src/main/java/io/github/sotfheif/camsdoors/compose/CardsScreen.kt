package io.github.sotfheif.camsdoors.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.sotfheif.camsdoors.MainViewModel
import io.github.sotfheif.camsdoors.data.CardDb
import kotlinx.coroutines.ExperimentalCoroutinesApi


const val ACTION_ITEM_SIZE = 56
const val CARD_HEIGHT = 56
const val CARD_OFFSET = 168f // we have 3 icons in a row, so that's 56 * 3

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalCoroutinesApi
@Composable
fun CardsScreen(viewModel: MainViewModel) {
    val cards by viewModel.cards.observeAsState()
    val revealedCardIds by viewModel.revealedCardIdsList.observeAsState()

    Scaffold() { paddingValues ->
        LazyColumn(Modifier.padding(paddingValues)) {
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
                        cardHeight = CARD_HEIGHT.dp,
                        cardOffset = CARD_OFFSET.dp(),
                        onExpand = { viewModel.onItemExpanded(card.id) },
                        onCollapse = { viewModel.onItemCollapsed(card.id) },
                    )
                }
            }
        }
    }
}


