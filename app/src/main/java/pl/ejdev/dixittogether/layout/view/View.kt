package pl.ejdev.dixittogether.layout.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
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
            .padding(16.dp, 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        ) {
            Button(onClick = { navController.navigate("home") }) {
                Text(text = "Home")
            }
            Button(onClick = { navController.navigate("profile") }) {
                Text(text = "Profile")
            }
        }
        content()
    }
}