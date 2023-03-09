package abandonedstudio.app.tospace.core.data.remote.launches.ktor

import abandonedstudio.app.tospace.BuildConfig

object Routes {
    const val UPCOMING_LAUNCHES_URL = "${BuildConfig.SPACE_DEVS_BASE_URL}launch/upcoming/?limit=20&is_crewed=false&include_suborbital=true&related=false&hide_recent_previous=true"
    const val UPCOMING_LAUNCH_URL = "${BuildConfig.SPACE_DEVS_BASE_URL}launch/upcoming/?limit=1&mode=detailed&hide_recent_previous=true"
    const val PREVIOUS_LAUNCH_URL = "${BuildConfig.SPACE_DEVS_BASE_URL}launch/previous/?limit=1&mode=detailed&hide_recent_previous=true"
}