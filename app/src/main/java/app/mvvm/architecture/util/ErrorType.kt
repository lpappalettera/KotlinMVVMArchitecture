package app.mvvm.architecture.util

import androidx.annotation.StringRes
import app.mvvm.architecture.R

sealed class ErrorType(@StringRes val errorMessage: Int)

object NoInternet : ErrorType(R.string.error_no_internet)
object NetworkError : ErrorType(R.string.error_network)
object OtherError : ErrorType(R.string.other_error)