package abandonedstudio.app.tospace.domain.infrastructure

import abandonedstudio.app.tospace.ToSpaceApplication
import androidx.annotation.StringRes

object StringUtil {
    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String = ToSpaceApplication.resources.getString(resId, *formatArgs)
    fun getString(@StringRes resId: Int): String = ToSpaceApplication.resources.getString(resId)
}