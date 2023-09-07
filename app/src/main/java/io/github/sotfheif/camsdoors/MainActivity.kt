@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

package io.github.sotfheif.camsdoors

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.sotfheif.camsdoors.ui.theme.CamsdoorsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CamsdoorsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scr()
                    //Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun camItem() {
    Surface(
        shape = RoundedCornerShape(16.dp),
        //color = MaterialTheme.colorScheme.surface
    ) {
        Column {
            Image(
                painter = painterResource(R.drawable.test_img),
                contentDescription = null,
                modifier = Modifier
                //.size(40.dp)
            )
            Text(
                text = "camIt",
                color = MaterialTheme.colorScheme.background
            )
        }
    }
}

@Composable
fun doorItem() {
    Surface(
        shape = RoundedCornerShape(16.dp)
    ) {
        Column {
            Text(
                text = "doorIt",
                color = MaterialTheme.colorScheme.background
            )
            Image(
                painter = painterResource(R.drawable.test_img),
                contentDescription = null,
                modifier = Modifier
                //.size(40.dp)
            )
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

@Composable
fun Scr() {
    Column {
        TabRow(selectedTabIndex = 0) {
            Text(text = "Камеры")
            Text(text = "Двери")
        }

        val pagerState = rememberPagerState()
        HorizontalPager(
            state = pagerState,
            pageCount = 2,
            beyondBoundsPageCount = 1,
        ) { page ->
            // Our page content
            if (page == 0) {
                camItem()
            } else {
                doorItem()
            }
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

/*
@Composable
fun TabRow() {
Row{
    Text(text = "Камеры")
    Text(text = "Двери")
}
}
*/
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CamsdoorsTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun doorItemPreview() {
    CamsdoorsTheme {
        doorItem()
    }
}

