package abandonedstudio.app.tospace.core.data.remote.spacex

import abandonedstudio.app.tospace.core.data.remote.spacex.dto.response.SpaceXDetailedLaunchResponse
import abandonedstudio.app.tospace.core.data.remote.spacex.dto.response.SpaceXLaunchResponse

interface SpaceXRemoteApi {

    suspend fun getNextLaunch(): SpaceXDetailedLaunchResponse

    suspend fun getLastLaunch(): SpaceXDetailedLaunchResponse

    suspend fun loadUpcomingLaunches(page: Int, limit: Int): SpaceXLaunchResponse
}