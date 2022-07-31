package abandonedstudio.app.tospace.core.domain.util.resources

import abandonedstudio.app.tospace.ToSpaceApplication
import androidx.annotation.StringRes

object StringUtil {
    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String = ToSpaceApplication.resources.getString(resId, *formatArgs)
    fun getString(@StringRes resId: Int): String = ToSpaceApplication.resources.getString(resId)
}