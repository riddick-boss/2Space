package abandonedstudio.app.tospace.core.data.remote.launches

import abandonedstudio.app.tospace.core.data.remote.launches.dto.AllLaunchesResponse

interface LaunchesRemoteApi {

    suspend fun loadUpcomingLaunches(next: String?): AllLaunchesResponse
}