package domain.base

interface Encryptable {
    fun encrypt(): Result<String>
}