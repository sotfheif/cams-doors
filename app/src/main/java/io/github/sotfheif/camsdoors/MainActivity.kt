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
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import coil.request.ImageRequest
import io.github.sotfheif.camsdoors.data.CamsDoorsRepository
import io.github.sotfheif.camsdoors.ui.theme.CamsdoorsTheme
import io.github.sotfheif.camsdoors.ui.theme.Grey20
import io.github.sotfheif.camsdoors.ui.theme.Grey50
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private const val TEST_IMAGE_URL =
    "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/Cat_poster_1.jpg/1280px-Cat_poster_1.jpg"
private const val TEST_IMAGE_URL2 =
    "https://serverspace.ru/wp-content/uploads/2019/06/backup-i-snapshot.png"


class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val app = (application as CamsDoorsApplication)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val camsDoorsRepository = app.container.camsDoorsRepository
        req(camsDoorsRepository)



        setContent {
            CamsdoorsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scr(viewModel)
                    //Greeting("Android")
                }
            }
        }
    }
}

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

@Composable
fun DoorItem() {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(7.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                ItemImageTest(TEST_IMAGE_URL)
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
                                fontSize = 12.sp,
                                //.modifier = Modifier
                                //.wrapContentSize(unbounded = true),
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                    ) {
                        LockImage()
                    }
                }
            }
        }
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

    Column {
        val coroutineScope = rememberCoroutineScope()
        val pagerState = rememberPagerState()
        val tabTexts = arrayOf("Камеры", "Двери")

        TabRow(selectedTabIndex = activeTab.value ?: 0) {
            tabTexts.forEachIndexed { index, name ->
                Tab(
                    selected = activeTab.value == index,
                    onClick = {
                        viewModel.switchTab(index);
                        coroutineScope.launch { pagerState.animateScrollToPage(index) }
                    },
                    content = {
                        Text(text = name)
                    }
                )
            }
            /*
            Tab(
                selected = activeTab.value == 0,
                onClick = {
                    viewModel.switchTab(0);
                    coroutineScope.launch { pagerState.animateScrollToPage(0) }
                },
                content = {
                    Text(text = "Камеры")
                }
            )
            Tab(
                selected = activeTab.value == 1,
                onClick = {
                    viewModel.switchTab(1);
                    coroutineScope.launch { pagerState.animateScrollToPage(1) }
                },
                content = {
                    Text(text = "Двери")
                })

             */
        }

        HorizontalPager(
            state = pagerState,
            pageCount = 2,
            beyondBoundsPageCount = 1,
            userScrollEnabled = false,
        ) { page ->
            if (page == 0) {
                CamItem()
            } else {
                DoorItem()
            }
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

@Composable
fun ItemImage(url: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        //placeholder = painterResource(R.drawable.placeholder),
        contentDescription = stringResource(R.string.item_image_desc),
        modifier = Modifier.fillMaxWidth(),
        //contentScale = ContentScale.Crop,
    )
}

@Composable
fun ItemImageTest(th: String = "") {
    Image(
        painter = painterResource(R.drawable.test_img_2),
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = Modifier.fillMaxWidth(),
        //.size(40.dp)
    )
}

fun req(camsDoorsRepository: CamsDoorsRepository) {
    val scope = CoroutineScope(Dispatchers.Main)
    scope.launch {
        camsDoorsRepository.getCamsDoors()
    }
}

@Composable
fun LockImage(isLocked: Boolean = false) {
    val res = if (isLocked) R.drawable.lock_on else R.drawable.lock_off
    Image(
        painter = painterResource(res),
        contentDescription = null,
    )
}

@Preview(showBackground = true)
@Composable
fun DoorItemPreview() {
    CamsdoorsTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            DoorItem()
        }
    }
}

