@file:OptIn(ExperimentalTextApi::class)

package pl.ejdev.dixittogether

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import pl.ejdev.dixittogether.shared.SUB_TITLE
import pl.ejdev.dixittogether.shared.TITLE
import pl.ejdev.dixittogether.shared.Title
import pl.ejdev.dixittogether.ui.button.GameButton
import pl.ejdev.dixittogether.ui.theme.DixitTogetherTheme

private val startScreenBackground = Color(253, 147, 0)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DixitTogetherTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = startScreenBackground)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 36.dp)
                        ) {
                            Title(TITLE, size = 24.em, lineHeight = 1.5.em)
                        }
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 36.dp)
                        ) {
                            Title(SUB_TITLE, size = 14.em, lineHeight = 1.em)
                        }
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 36.dp)
                        ) {
                            GameButton(
                                "Start"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DixitTogetherTheme {
        Title(TITLE, 24.em)
    }
}