package abandonedstudio.app.tospace.core.data.remote.launches

import abandonedstudio.app.tospace.core.data.remote.launches.dto.AllLaunchesResponse
import abandonedstudio.app.tospace.core.data.remote.launches.dto.LaunchDetailedResponse

interface LaunchesRemoteApi {

    suspend fun fetchNextLaunch(): LaunchDetailedResponse

    suspend fun fetchPreviousLaunch(): LaunchDetailedResponse

    suspend fun loadUpcomingLaunches(next: String?): AllLaunchesResponse
}