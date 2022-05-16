package abandonedstudio.app.tospace.core.domain.repository

import abandonedstudio.app.tospace.core.domain.model.Launch

interface SpaceXRepository {

    suspend fun getLastLaunch(): Launch

    suspend fun getNextLaunch(): Launch
}