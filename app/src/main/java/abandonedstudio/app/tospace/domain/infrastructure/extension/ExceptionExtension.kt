package abandonedstudio.app.tospace.domain.infrastructure.extension

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.domain.infrastructure.util.StringUtil
import kotlin.coroutines.cancellation.CancellationException

fun Exception.toMessage(): String? =
    when(this) {
        is CancellationException -> null
        else -> StringUtil.getString(R.string.fetch_fail_message)
    }