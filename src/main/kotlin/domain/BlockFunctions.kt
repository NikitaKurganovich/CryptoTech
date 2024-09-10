package domain

import com.jogamp.common.util.UnsafeUtil
import dev.bababnanick.crypto_decoding.generated.resources.Res
import dev.bababnanick.crypto_decoding.generated.resources.empty_warning
import dev.bababnanick.crypto_decoding.generated.resources.format_error
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString

fun <T> runWithChecks(
    vararg additionalCheck: Pair<Boolean, Unit>?,
    message: String,
    block: () -> T
): T = when {
    message.contains(Regexes.specialCharacters) -> runBlocking {
        error(getString(Res.string.format_error))
    }

    message.isEmpty() -> runBlocking {
        error(getString(Res.string.empty_warning))
    }

//    additionalCheck.forEach { pair ->
//        pair?.let {
//            it.first ->
//        }
//    }

    else -> block()
}