package domain.base

interface Cipher<T>: Encryptable {
    val message: String
    val key: T
}