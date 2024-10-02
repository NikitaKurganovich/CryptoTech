package dev.example.crypto.domain.base

interface Encryptable {
    fun encrypt(): Result<String>
}