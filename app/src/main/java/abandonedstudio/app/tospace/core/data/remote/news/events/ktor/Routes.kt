package abandonedstudio.app.tospace.core.data.remote.news.events.ktor

import abandonedstudio.app.tospace.BuildConfig

object Routes {

    const val EVENTS_URL = "${BuildConfig.SPACE_DEVS_BASE_URL}event/upcoming/?limit=40"
}