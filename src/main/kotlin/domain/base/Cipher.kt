package domain.base

interface Cipher<T> {
    val message: String
    val key: T
    fun encrypt(): Result<String>
}