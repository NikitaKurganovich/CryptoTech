package dev.crypto.base.resources

import androidx.compose.runtime.Composable

sealed interface Resource<out T>{
     @Composable
     operator fun invoke(): T
}
