package pl.ejdev.dixittogether.features.core.view.widgets

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import pl.ejdev.dixittogether.features.core.shared.toColor
import pl.ejdev.dixittogether.features.core.view.pages.View

@Composable
fun StylizedButton(onClick: () -> Unit) {
    val roundedCornerShape = RoundedCornerShape(4.dp)
    val widthFactor = 10
    val externalWidth = 10
    "#0aad3f".toColor()
    Surface(
        modifier = Modifier
            .width((externalWidth * widthFactor).dp)
            .height(60.dp)
            .clip(roundedCornerShape)
            .shadow(
                4.dp,
                roundedCornerShape,
//                clip = true,
                ambientColor = Color.White
            )
            .padding(1.dp),
        shape = roundedCornerShape,
        color = Color.White
    ) {
        Surface(
            modifier = Modifier
                .width((6 * widthFactor).dp)
                .height((6 * widthFactor).dp)
                .shadow(2.dp, roundedCornerShape, ambientColor =  Color.White)
                .padding(1.dp),
            shape = roundedCornerShape,
            color = "#0aad3f".toColor()
        ) {
        }
    }
}

/*
  <KBtn className="w-10 items-end justify-start pl-[4px] pb-[2px]"
childrenClassName="items-start"
                --------------------------
className "p-[0.5px] rounded-[4px]", backlit && "bg-white/[0.2] shadow-xl shadow-white"
            <div
                className={cn(
                    "h-6 w-6 bg-[#0A090D] rounded-[3.5px] flex items-center justify-center",
                    className
                )}
                style={{
                    boxShadow:
                        "0px -0.5px 2px 0 #0D0D0F inset, -0.5px 0px 2px 0 #0D0D0F inset",
                }}
            >
                <div
                    className={cn(
                        "text-neutral-200 text-[5px] w-full flex justify-center items-center flex-col",
                        childrenClassName,
                        backlit && "text-white"
                    )}


 */
@Composable
@Preview(showBackground = true)
fun StylizedButtonPreview() {
    val navController = rememberNavController()
    View(navController = navController) {
        StylizedButton {

        }
    }
}