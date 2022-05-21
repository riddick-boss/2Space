package abandonedstudio.app.tospace.core.data.remote.spacex

import abandonedstudio.app.tospace.core.data.remote.spacex.dto.response.SpaceXSingleLaunchResponse

interface SpaceXRemoteApi {

    suspend fun getNextLaunch(): SpaceXSingleLaunchResponse

    suspend fun getLastLaunch(): SpaceXSingleLaunchResponse
}