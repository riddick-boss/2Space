package abandonedstudio.app.tospace.core.domain.repository

import abandonedstudio.app.tospace.core.domain.model.Launch

interface SpaceXRepository {

    suspend fun getNextLaunch(): Launch

    suspend fun getLastLaunch(): Launch
}