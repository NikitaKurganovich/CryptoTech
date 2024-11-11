package dev.crypto.base.interfaces

interface Cipher<T>: Encryptable {
    val message: String
    val key: T
}