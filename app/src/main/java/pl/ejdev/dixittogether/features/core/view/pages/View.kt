package pl.ejdev.dixittogether.features.core.view.pages

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pl.ejdev.dixittogether.features.core.shared.Island_Moments
import pl.ejdev.dixittogether.features.core.shared.TITLE
import pl.ejdev.dixittogether.features.core.shared.componentColor
import pl.ejdev.dixittogether.features.core.view.widgets.DixitButton
import pl.ejdev.dixittogether.features.core.shared.mainBgColor

@Composable
internal fun View(
    navController: NavHostController,
    content: @Composable ColumnScope.() -> Unit
) {
    val bottomColor = Color.hsl(0.63f, 0.28f, 0.23f)
    val brush = Brush.verticalGradient(
        .05f to mainBgColor,
        .6f to Color(57, 64, 111),
        .95f to bottomColor,
    )

    Scaffold(
        topBar = { TopApplicationBar(navController) },
        bottomBar = { BottomApplicationBar(bottomColor) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(brush),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                content()
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopApplicationBar(navController: NavHostController) {
    TopAppBar(
        modifier = Modifier
            .background(mainBgColor)
            .clip(
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomEnd = 25.dp,
                    bottomStart = 25.dp
                )
            ),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = componentColor,
            titleContentColor = Color.White,
        ),
        title = {
            Text(
                text = TITLE,
                color = Color.White,
                fontFamily = Island_Moments,
                textAlign = TextAlign.Right,
                fontSize = 36.sp,
                modifier = Modifier
                    .offset(x = 8.dp, y = 8.dp)
                    .fillMaxWidth(fraction = 0.6f)
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { navController.navigate("profile") },
                modifier = Modifier.semantics { testTag = "profile-btn" }
            ) {
                Icon(
                    imageVector = Filled.AccountCircle,
                    contentDescription = "Home",
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(
                onClick = { navController.navigate("home") },
                modifier = Modifier.semantics { testTag = "home-btn" }
            ) {
                Icon(
                    imageVector = Filled.Home,
                    contentDescription = "Home",
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
private fun BottomApplicationBar(bottomColor: Color) {
    BottomAppBar(
        modifier = Modifier
            .background(bottomColor)
            .clip(
                shape = RoundedCornerShape(
                    topStart = 25.dp,
                    topEnd = 25.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp
                )
            ),
        containerColor = componentColor
    ) {

    }
}

@Preview(
    name = "view preview",
    backgroundColor = 0xFFFFFFFF,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
internal fun ViewPreview() {
    val navController = rememberNavController()
    View(navController = navController) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DixitButton(text = "hello")
            DixitButton(text = "hello")
            DixitButton(text = "hello")
        }
    }
}