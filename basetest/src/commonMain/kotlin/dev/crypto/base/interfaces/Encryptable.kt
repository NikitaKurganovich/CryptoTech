package dev.crypto.base.interfaces

interface Encryptable {
    fun encrypt(): Result<String>
    fun decrypt(): Result<String>
}