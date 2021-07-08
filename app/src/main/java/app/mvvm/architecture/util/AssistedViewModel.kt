package app.mvvm.architecture.util

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import app.mvvm.architecture.di.AssistedViewModelFactoryProvider
import dagger.assisted.AssistedInject
import dagger.hilt.android.EntryPointAccessors

/**
 * Returns an existing [AssistedInject]-annotated [ViewModel] or creates a new one based on the
 * current view model store owner.
 */
@Composable
inline fun <reified VM : ViewModel> assistedViewModel(
    viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    },
    provideFactory: AssistedViewModelFactoryProvider.() -> ViewModelProvider.Factory,
): VM {
    val factory = provideFactory(assistedViewModelFactory())
    return viewModel(viewModelStoreOwner, factory = factory)
}

/**
 * Returns the [AssistedViewModelFactoryProvider] of the activity from the current [LocalContext].
 */
@Composable
fun assistedViewModelFactory() = EntryPointAccessors.fromActivity(
    LocalContext.current as Activity,
    AssistedViewModelFactoryProvider::class.java
)