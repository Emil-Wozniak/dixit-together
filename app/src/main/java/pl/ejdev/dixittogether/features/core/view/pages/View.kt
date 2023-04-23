package pl.ejdev.dixittogether.features.core.view.pages

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pl.ejdev.dixittogether.features.core.shared.TITLE
import pl.ejdev.dixittogether.features.core.shared.Title
import pl.ejdev.dixittogether.startScreenBackground

@Composable
internal fun View(
    navController: NavHostController,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = startScreenBackground)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 1.dp)
                .background(Color.White)
        ) {
            Box {
                Title(
                    text = TITLE,
                    size = 30.sp,
                    modifier = Modifier
                        .offset(x = 8.dp, y = 8.dp)
                        .fillMaxWidth(fraction = 0.6f)
                )
            }
            Box(contentAlignment = Alignment.TopEnd) {
                Row {
                    IconButton(
                        onClick = { navController.navigate("home") },
                        modifier = Modifier
                            .semantics { testTag = "home-btn" }
                    ) {
                        Icon(
                            imageVector = Filled.Home,
                            contentDescription = "Home"
                        )
                    }
                    IconButton(
                        onClick = { navController.navigate("profile") },
                        modifier = Modifier.semantics { testTag = "profile-btn" }
                    ) {
                        Icon(
                            imageVector = Filled.AccountCircle,
                            contentDescription = "Home"
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(bottom = 8.dp))
        Column(modifier = Modifier.padding(8.dp)) {
            content()
        }
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

    }
}