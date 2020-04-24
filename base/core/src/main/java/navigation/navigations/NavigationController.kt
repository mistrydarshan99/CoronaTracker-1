package navigation.navigations

import androidx.fragment.app.FragmentActivity
import java.lang.ref.WeakReference

interface NavigationController {
    val activity: WeakReference<FragmentActivity>

    fun close() = activity.get()?.onBackPressed()

    fun finish() = activity.get()?.finish()

    fun navigateToDashBoard()
}