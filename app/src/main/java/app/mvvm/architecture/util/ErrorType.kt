package app.mvvm.architecture.util

import android.view.View
import androidx.annotation.StringRes
import app.mvvm.architecture.R
import com.google.android.material.snackbar.BaseTransientBottomBar
import splitties.snackbar.snack

sealed class ErrorType(@StringRes val errorMessage: Int) {
    fun showSnackBar(view: View) {
        view.snack(errorMessage, BaseTransientBottomBar.LENGTH_SHORT).show()
    }
}

object NoInternet : ErrorType(R.string.error_no_internet)
object NetworkError : ErrorType(R.string.error_network)
object OtherError : ErrorType(R.string.other_error)