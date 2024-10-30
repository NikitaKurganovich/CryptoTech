package dev.crypto.base.resources

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

class SResource(
    private val resource: StringResource,
    vararg val formatArgs: Any = arrayOf()
) : Resource<String> {
    @Composable
    override fun invoke(): String = stringResource(resource, formatArgs)
}