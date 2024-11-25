package androidkit.kit

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.crypto.ui.theme.CipherTheme

@Composable
fun CipherCheckbox(
    modifier: Modifier = Modifier,
    edgeValues: EdgeValues = EdgeValues(
        horizontal = CipherTheme.viewDimensions.checkboxCornerEdgeLength,
        vertical = CipherTheme.viewDimensions.checkboxCornerEdgeLength
    ),
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = MutableInteractionSource()
) {
    Checkbox(
        modifier = modifier
            .customBorder(
                edges = edgeValues,
                innerColor = CipherTheme.colors.border,
                width = CipherTheme.viewDimensions.borderWidth,
                edgeColor = CipherTheme.colors.borderCorner,
                isFocused = checked
            )
            .size(CipherTheme.viewDimensions.checkboxSize),
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = CheckboxDefaults.colors(
            checkedColor = Color.Transparent,
            uncheckedColor = Color.Transparent,
            checkmarkColor = CipherTheme.colors.borderCorner
        ),
        enabled = enabled,
        interactionSource = interactionSource
    )
}

@Preview
@Composable
fun CheckboxPreview() = CipherTheme {
    Column(
        modifier = Modifier.wrapContentSize().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CipherCheckbox(
            modifier = Modifier.padding(4.dp),
            checked = true,
            onCheckedChange = {}
        )
        CipherCheckbox(
            modifier = Modifier.padding(4.dp),
            checked = false,
            onCheckedChange = {}
        )
    }
}

@Preview
@Composable
fun ClickableCheckboxPreview() = CipherTheme {
    Column(
        modifier = Modifier.wrapContentSize().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var checkedFirst by remember { mutableStateOf(true) }
        var checkedSecond by remember { mutableStateOf(false) }
        CipherCheckbox(
            modifier = Modifier.padding(4.dp),
            checked = checkedFirst,
            onCheckedChange = {checkedFirst = it}
        )
        CipherCheckbox(
            modifier = Modifier.padding(4.dp),
            checked = checkedSecond,
            onCheckedChange = {checkedSecond = it}
        )
    }
}
