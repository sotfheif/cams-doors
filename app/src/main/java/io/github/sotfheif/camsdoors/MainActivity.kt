//todo add elevation or shadow to tabrow indicator
//todo review markup (like paddings)


@file:OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class
)

package io.github.sotfheif.camsdoors

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.pullrefresh.PullRefreshIndicator
import androidx.compose.material3.pullrefresh.pullRefresh
import androidx.compose.material3.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import io.github.sotfheif.camsdoors.compose.DraggableCardsColumn
import io.github.sotfheif.camsdoors.data.CamsDoorsRepository
import io.github.sotfheif.camsdoors.ui.theme.Black
import io.github.sotfheif.camsdoors.ui.theme.CamsdoorsTheme
import io.github.sotfheif.camsdoors.ui.theme.LightGrey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


const val TEST_IMAGE_URL =
    "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/Cat_poster_1.jpg/1280px-Cat_poster_1.jpg"
const val TEST_IMAGE_URL2 =
    "https://serverspace.ru/wp-content/uploads/2019/06/backup-i-snapshot.png"


class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val app = (application as CamsDoorsApplication)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val camsDoorsRepository = app.container.camsDoorsRepository
        viewModel.loadUiData(camsDoorsRepository)
        req(camsDoorsRepository)



        setContent {
            CamsdoorsTheme {

                val refreshScope = rememberCoroutineScope()
                var refreshing by remember { mutableStateOf(false) }
                var itemCount by remember { mutableIntStateOf(15) }

                fun refresh() = refreshScope.launch {
                    refreshing = true
                    delay(1500)
                    itemCount = itemCount + 5
                    refreshing = false
                }

                val state = rememberPullRefreshState(refreshing, ::refresh)

                //DraggableCardsColumn(viewModel)

                /*
                Box(Modifier.pullRefresh(state)) {
                    LazyColumn(Modifier.fillMaxSize()) {
                        if (!refreshing) {
                            items(itemCount) {
                                ListItem(headlineText = { Text(text = "Item ${itemCount - it}") })
                            }
                        }
                    }

                    PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
                }
                */
                ///*
                // mb better use scaffold
                Column {
                    CenterAlignedTopAppBar()
                    PlaceOnSurface { Scr(viewModel) }
                }

                //*/

                //CenterAlignedTopAppBar({ PlaceOnSurface{ Scr(viewModel) } })
                /*
                val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

                Scaffold(
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

                    topBar = {
                        CenterAlignedTopAppBar(
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text(
                                    "Мой дом",
                                    maxLines = 1,
                                    //overflow = TextOverflow.Ellipsis
                                )
                            },
                            scrollBehavior = scrollBehavior,
                        )
                    },
                ) { innerPadding ->
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding)) {
                        PlaceOnSurface { Scr(viewModel) }
                    }
                }

 */
            }
        }
    }
}

@Composable
fun PlaceOnSurface(content: @Composable () -> Unit) {
// A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        content()
        //Greeting("Android")
    }
}

/*
@Composable
fun screen{
    // Display 10 items
    val pagerState = rememberPagerState(pageCount = {
        10
    })
    HorizontalPager(state = pagerState) { page ->
        // Our page content
        Text(
            text = "Page: $page",
            modifier = Modifier.fillMaxWidth()
        )
    }
}
*/

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Scr(viewModel: MainViewModel) {
    val activeTab = viewModel.activeTab.observeAsState()

    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }
    var itemCount by remember { mutableIntStateOf(6) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        delay(1500)
        itemCount = itemCount + 5
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)

    //DraggableCardsColumn(viewModel)

    /*
    Box(Modifier.pullRefresh(state)) {
        LazyColumn(Modifier.fillMaxSize()) {
            if (!refreshing) {
                items(itemCount) {
                    ListItem(headlineText = { Text(text = "Item ${itemCount - it}") })
                }
            }
        }

        PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
    }
    */
    Column {
        val coroutineScope = rememberCoroutineScope()
        val pagerState = rememberPagerState()
        val tabTexts = arrayOf("Камеры", "Двери")

        TabRow(
            selectedTabIndex = activeTab.value ?: 0,
            containerColor = LightGrey
            //indicator =
        ) {
            tabTexts.forEachIndexed { index, name ->
                Tab(
                    selected = activeTab.value == index,
                    onClick = {
                        viewModel.switchTab(index)
                        coroutineScope.launch { pagerState.animateScrollToPage(index) }
                    },
                    content = {
                        Text(text = name, color = Black)
                    },
                    selectedContentColor = Black,
                    modifier = Modifier//.height(30.dp)
                        .padding(vertical = 10.dp)
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            pageCount = 2,
            beyondBoundsPageCount = 1,
            userScrollEnabled = false,
        ) { page ->
                Box(
                    Modifier.pullRefresh(state)
                        .clipToBounds()
                ) {
                    DraggableCardsColumn(viewModel, refreshing)
                    /*
                    LazyColumn(Modifier.fillMaxSize()) {
                        if (!refreshing) {
                            items(itemCount) {
                                DoorItem()
                            }
                        }
                    }
*/
                    PullRefreshIndicator(
                        refreshing, state,
                        Modifier.align(Alignment.TopCenter)
                            .offset(y = (-40).dp)
                    )
                }
                /*
                LazyColumn {
                    items(6) {
                        DoorItem()
                    }
                }
                */
        }

        Button(
            onClick = {
                //viewModel.switchTab(if(viewModel.activeTab.value==0) 1 else 0)

                coroutineScope.launch {
                    pagerState.animateScrollToPage(if (viewModel.activeTab.value == 0) 1 else 0)
                }
            },
            content = {
                Text("Test")
            }

        )
    }
}



fun req(camsDoorsRepository: CamsDoorsRepository) {
    val scope = CoroutineScope(Dispatchers.Main)
    scope.launch {
        camsDoorsRepository.getCamsDoors()
    }
}


@Preview(showBackground = true)
@Composable
fun DoorItemPreview() {
    CamsdoorsTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            //DoorItem()
        }
    }
}

