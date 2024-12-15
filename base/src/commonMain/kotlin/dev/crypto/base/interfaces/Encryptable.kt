package dev.crypto.base.interfaces

import dev.crypto.base.resources.ResultMessage

interface Encryptable {
    fun encrypt(): Result<ResultMessage>
    fun decrypt(): Result<ResultMessage>
}