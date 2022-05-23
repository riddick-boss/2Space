package abandonedstudio.app.tospace.core.domain.repository

import abandonedstudio.app.tospace.core.domain.model.DetailedLaunch
import abandonedstudio.app.tospace.core.domain.model.Launch
import abandonedstudio.app.tospace.core.domain.util.DefaultPagingSource

interface SpaceXRepository {

    suspend fun getNextLaunch(): DetailedLaunch

    suspend fun getLastLaunch(): DetailedLaunch

    suspend fun loadUpcomingLaunches(page: Int, limit: Int = 10): DefaultPagingSource.Page<Launch>
}