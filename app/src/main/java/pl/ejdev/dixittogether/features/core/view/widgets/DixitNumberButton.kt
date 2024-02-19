package pl.ejdev.dixittogether.features.core.view.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import pl.ejdev.dixittogether.features.core.shared.Island_Moments
import pl.ejdev.dixittogether.features.core.shared.componentColor
import pl.ejdev.dixittogether.features.core.shared.fadingEdge
import pl.ejdev.dixittogether.features.core.shared.mainBgColor

@Composable
internal fun DixitNumberButton(
    value: MutableState<Int>,
    modifier: Modifier = Modifier
) {
    val linearGradient = Brush.linearGradient(
        0.1f to componentColor.copy(alpha = 0.8f),
        0.7f to componentColor,
    )
    val roundedCornerShape = RoundedCornerShape(20)
    val fullWidth = 120
    val fullHeight = 48
    val widthHalf = fullWidth / 2
    val heightHalf = fullHeight / 2
    Row(
        modifier = Modifier.fillMaxWidth().height(fullHeight.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.height(fullWidth.dp)
        ) {
            Surface(
                modifier = Modifier
                    .height(fullHeight.dp)
                    .width(fullWidth.dp),
                shape = roundedCornerShape,
            ) {
                Row {
                    // buttons
                    Column(
                        modifier = Modifier
                            .height(fullWidth.dp)
                            .width(widthHalf.dp)
                    ) {
                        Surface(
                            modifier = Modifier
                                .height(heightHalf.dp)
                                .width(fullWidth.dp)
                                .fadingEdge(linearGradient)
                                .background(componentColor),
                            shape = roundedCornerShape,
                        ) {
                            IconButton(
                                onClick = { value.value = value.value + 1 },
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(componentColor)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.KeyboardArrowUp,
                                    contentDescription = "add number",
                                    modifier = modifier,
                                    tint = Color.White
                                )
                            }
                        }
                        Surface(
                            modifier = Modifier
                                .height(heightHalf.dp)
                                .width(fullWidth.dp)
                                .fadingEdge(linearGradient)
                                .background(componentColor),
                            shape = roundedCornerShape,
                        ) {
                            IconButton(
                                onClick = { value.value = value.value - 1 },
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(componentColor)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.KeyboardArrowDown,
                                    contentDescription = "add number",
                                    modifier = modifier,
                                    tint = Color.White
                                )
                            }
                        }
                    }
                    // value
                    Column(
                        modifier = Modifier
                            .height(fullWidth.dp)
                            .width(widthHalf.dp)
                    ) {
                        Surface(
                            modifier = Modifier
                                .height(fullWidth.dp)
                                .fadingEdge(linearGradient)
                                .background(componentColor),
                            shape = roundedCornerShape,
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(componentColor)
                            ) {
                                Text(
                                    "${value.value + 1}",
                                    color = Color.DarkGray,
                                    fontFamily = Island_Moments,
                                    fontSize = 3.em,
                                    lineHeight = 1.em,
                                )
                                Text(
                                    "${value.value}",
                                    color = Color.White,
                                    fontFamily = Island_Moments,
                                    fontSize = 4.em,
                                    lineHeight = 1.em,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    "${value.value - 1}",
                                    color = Color.DarkGray,
                                    fontFamily = Island_Moments,
                                    fontSize = 3.em,
                                    lineHeight = 1.em,
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}

@Preview
@Composable
internal fun DixitNumberButtonPreview() {
    val state = remember {
        mutableIntStateOf(1)
    }
    Surface(
        modifier = Modifier.size(600.dp),
        color = mainBgColor
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DixitNumberButton(state)
        }
    }
}