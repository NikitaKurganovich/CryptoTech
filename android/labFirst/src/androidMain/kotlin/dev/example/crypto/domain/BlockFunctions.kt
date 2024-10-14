package dev.example.crypto.domain

fun <T> checkMessage(
    vararg additionalCheck: AdditionCheck<T>? = arrayOf(null),
    message: String,
    block: () -> T
): T {
    if (message.isEmpty()) error(InputErrors.MessageEmpty)
    if (message.contains(Regexes.onlyAlphabet)) {
        error(InputErrors.MessageFormat)
    }
    additionalCheck.forEach { check->
        check?.let {
            if (it.predicate) {
                return it.action()
            }
        }
    }
    return block.invoke()
}

fun <T> checkStringKey(
    vararg additionalCheck: AdditionCheck<T>? = arrayOf(null),
    key: String,
    block: () -> T
): T {
    if (key.isEmpty()) error(InputErrors.KeyEmpty)
    if (key.contains(Regexes.onlyAlphabet)) {
        error(InputErrors.KeyFormat)
    }
    additionalCheck.forEach { check->
        check?.let {
            if (it.predicate) {
                return it.action()
            }
        }
    }
    return block.invoke()
}

data class AdditionCheck<T>(
    val predicate: Boolean,
    val action: () -> T
)