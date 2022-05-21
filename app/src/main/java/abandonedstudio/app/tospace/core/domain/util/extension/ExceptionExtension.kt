package abandonedstudio.app.tospace.core.domain.util.extension

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.domain.util.resources.StringUtil

fun Exception.toMessage(): String = StringUtil.getString(R.string.fetch_fail_message) // TODO: message based on exception type ?