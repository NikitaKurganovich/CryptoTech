package androidkit.kit

import dev.crypto.ui.theme.CipherTheme
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationVector
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import dev.crypto.shared.ui.R

@Composable
fun CipherSwitch(
    modifier: Modifier = Modifier,
    firstOptionText: String,
    secondOptionText: String,
    isFirstSelected: Boolean = true,
    onClick: (Boolean) -> Unit
) {
    var isFirstSelected by remember { mutableStateOf(isFirstSelected) }
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

@Composable
fun rememberAnimatedRotateDegree(
    defaultValue: Float,
    activeValue: Float,
    isActive: Boolean
): State<Float> {
    val targetValue by remember(isActive) {
        derivedStateOf {
            if (isActive) {
                activeValue
            } else {
                defaultValue
            }
        }
    }
    return animateFloatAsState(
        targetValue = targetValue,
        animationSpec = tween(
            durationMillis = 1000,
        ),
        label = ""
    )
}

@Composable
fun rememberAnimatedTextUnit(
    defaultValue: TextUnit,
    activeValue: TextUnit,
    isActive: Boolean
): State<TextUnit> {
    val targetValue by remember(isActive) {
        derivedStateOf {
            if (isActive) {
                activeValue
            } else {
                defaultValue
            }
        }
    }
    return animateValueAsState(
        targetValue = targetValue,
        animationSpec = tween(
            durationMillis = 1000,
        ),
        label = "",
        typeConverter = twoWayTextUnitConverter
    )
}

@Composable
fun rememberAnimatedEnabledTextColor(
    defaultValue: Color,
    activeValue: Color,
    isActive: Boolean
): State<Color> {
    val targetValue by remember(isActive) {
        derivedStateOf {
            if (isActive) {
                activeValue
            } else {
                defaultValue
            }
        }
    }
    return animateColorAsState(
        targetValue = targetValue,
        animationSpec = tween(
            durationMillis = 1000,
        ),
        label = ""
    )
}

val twoWayTextUnitConverter = TwoWayConverter<TextUnit, AnimationVector1D>(
    convertToVector = { AnimationVector(it.value) },
    convertFromVector = { AnimationVector1D(it.value).value.sp }
)


@Preview
@Composable
fun SwitchPreview() {
    CipherTheme {
        CipherSwitch(
            modifier = Modifier.fillMaxWidth(),
            firstOptionText = "Test1",
            secondOptionText = "Test2",
            onClick = {}
        )
    }
}