package abandonedstudio.app.tospace.core.domain.util.extension

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.domain.util.resources.StringUtil
import kotlin.coroutines.cancellation.CancellationException

fun Exception.toMessage(): String? =
    when(this) {
        is CancellationException -> null
        else -> StringUtil.getString(R.string.fetch_fail_message)
    }