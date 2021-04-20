package app.mvvm.architecture.util.extensions

import app.mvvm.architecture.util.ErrorType
import app.mvvm.architecture.util.NetworkError
import app.mvvm.architecture.util.NoInternet
import app.mvvm.architecture.util.OtherError
import retrofit2.HttpException
import timber.log.Timber
import java.net.SocketException

fun Throwable.logAndMapToErrorType(): ErrorType {
    Timber.e(this)
    return when (this) {
        is SocketException -> NoInternet
        is HttpException -> NetworkError
        else -> OtherError
    }
}