package abandonedstudio.app.tospace.domain.model.util

sealed class Result<T> { // workaround for Kotlin Result class, which has some issues with casting
    data class Success<T>(val value: T) : Result<T>()
    data class Failure<T>(val error: Throwable) : Result<T>()

    fun getOrNull() = when(this) {
        is Success -> value
        is Failure -> null
    }
}