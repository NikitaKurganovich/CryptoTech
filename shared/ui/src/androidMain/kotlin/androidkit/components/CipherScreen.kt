package androidkit.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.crypto.shared.ui.R
import dev.crypto.ui.theme.CipherTheme

@Composable
fun CipherScreen(
    screenContent: @Composable BoxScope.() -> Unit,
) {
    val widthInDp = CipherTheme.viewDimensions.borderWidth
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    var width by remember { mutableStateOf(widthInDp) }
    val animatedWidth by animateDpAsState(
        targetValue = width,
        animationSpec = tween(
            durationMillis = 2000,
            easing = FastOutSlowInEasing
        ),
        label = ""
    )

    LaunchedEffect(Unit) { width = screenWidth }
    Box(
        modifier = with(Modifier) {
            fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.bg_crypto),
                    contentScale = ContentScale.FillBounds
                )

        }
    ){
        Box(
            modifier = Modifier
                .width(animatedWidth)
                .clipToBounds(),
            content = screenContent
        )
    }
}