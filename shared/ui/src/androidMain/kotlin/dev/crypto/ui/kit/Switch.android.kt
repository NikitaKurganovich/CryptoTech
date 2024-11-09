package dev.crypto.ui.kit

import androidkit.kit.CipherText
import androidkit.kit.rememberAnimatedEnabledTextColor
import androidkit.kit.rememberAnimatedRotateDegree
import androidkit.kit.rememberAnimatedTextUnit
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import dev.crypto.shared.ui.R
import dev.crypto.ui.theme.CipherTheme

@Composable
actual fun CipherSwitch(
    modifier: Modifier,
    firstOptionText: String,
    secondOptionText: String,
    isFirst: Boolean,
    onClick: (Boolean) -> Unit
) {
    var isFirstSelected by remember { mutableStateOf(isFirst) }
    val rotateDegree by rememberAnimatedRotateDegree(
        defaultValue = 180f,
        activeValue = 0f,
        isActive = isFirstSelected
    )
    val firstTextUnit by rememberAnimatedTextUnit(
        defaultValue = 16.sp,
        activeValue = TextUnit(24f, TextUnitType.Sp),
        isActive = isFirstSelected
    )
    val secondTextUnit by rememberAnimatedTextUnit(
        defaultValue = 24.sp,
        activeValue = TextUnit(16f, TextUnitType.Sp),
        isActive = isFirstSelected
    )

    val firstColor by rememberAnimatedEnabledTextColor(
        defaultValue = Color.White,
        activeValue = Color.White.copy(alpha = 0.68f),
        isActive = isFirstSelected
    )
    val secondColor by rememberAnimatedEnabledTextColor(
        defaultValue = Color.White,
        activeValue = Color.White.copy(alpha = 0.68f),
        isActive = !isFirstSelected
    )
    Row(
        modifier = modifier
            .height(CipherTheme.dimensions.largePlus)
            .clickable {
                isFirstSelected = !isFirstSelected
                onClick(isFirstSelected)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        CipherText(
            text = firstOptionText,
            textStyle = CipherTheme.typography.default.copy(
                fontSize = firstTextUnit
            ),
            color = firstColor
        )
        Image(
            modifier = Modifier
                .size(CipherTheme.dimensions.largeDefault)
                .rotate(rotateDegree),
            painter = painterResource(id = R.drawable.switcher_crypto),
            contentDescription = null,
        )
        CipherText(
            text = secondOptionText,
            textStyle = CipherTheme.typography.default.copy(
                fontSize = secondTextUnit
            ),
            color = secondColor
        )
    }
}