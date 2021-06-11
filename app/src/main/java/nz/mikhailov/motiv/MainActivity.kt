package nz.mikhailov.motiv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nz.mikhailov.motiv.ui.theme.MotivTheme
import nz.mikhailov.motiv.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MotivTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    Scaffold(
        topBar = { MyTopBar() }
    ) { innerPadding ->
        Column(
            Modifier.padding(innerPadding).padding(16.dp)
        ) {
            Balance(21)
        }
    }
}

@Composable
fun MyTopBar() {
    TopAppBar(
        title = { Text("Motiv") },
    )
}

@Composable
fun Balance(balance: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
    ) {
        Text("Total is: ", style = Typography.h3)
        Text("$${balance}", style = Typography.h3)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MotivTheme {
        MainScreen()
    }
}