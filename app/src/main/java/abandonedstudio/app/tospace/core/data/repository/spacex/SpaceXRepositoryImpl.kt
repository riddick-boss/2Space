package abandonedstudio.app.tospace.core.data.repository.spacex

import abandonedstudio.app.tospace.core.data.remote.spacex.SpaceXRemoteApi
import abandonedstudio.app.tospace.core.domain.model.Launch
import abandonedstudio.app.tospace.core.domain.repository.SpaceXRepository
import javax.inject.Inject

class SpaceXRepositoryImpl @Inject constructor(
    private val remoteApi: SpaceXRemoteApi
) : SpaceXRepository {

    override suspend fun getNextLaunch(): Launch =
        remoteApi.getNextLaunch().toLaunch()

    override suspend fun getLastLaunch(): Launch =
        remoteApi.getLastLaunch().toLaunch()
}