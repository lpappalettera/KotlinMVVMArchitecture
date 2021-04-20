package app.mvvm.architecture.util

sealed class Resource<T>(
    val data: T? = null,
    val error: Event<ErrorType>? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(errorType: ErrorType, data: T? = null) : Resource<T>(data, Event(errorType))
}