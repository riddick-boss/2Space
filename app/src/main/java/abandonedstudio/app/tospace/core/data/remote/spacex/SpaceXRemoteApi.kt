package abandonedstudio.app.tospace.core.data.remote.spacex

import abandonedstudio.app.tospace.core.data.remote.spacex.dto.response.PastSpaceXLaunchesResponse
import abandonedstudio.app.tospace.core.data.remote.spacex.dto.response.SpaceXDetailedLaunchResponse
import abandonedstudio.app.tospace.core.data.remote.spacex.dto.response.UpcomingSpaceXLaunchesResponse

interface SpaceXRemoteApi {

    suspend fun getNextLaunch(): SpaceXDetailedLaunchResponse

    suspend fun getLastLaunch(): SpaceXDetailedLaunchResponse

    suspend fun loadUpcomingLaunches(page: Int, limit: Int): UpcomingSpaceXLaunchesResponse

    suspend fun loadPastLaunches(page: Int, limit: Int): PastSpaceXLaunchesResponse
}