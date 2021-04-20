package app.mvvm.architecture.util.extensions

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import app.mvvm.architecture.util.FragmentViewBindingDelegate

object FragmentExtensions {
    fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
        FragmentViewBindingDelegate(this, viewBindingFactory)
}