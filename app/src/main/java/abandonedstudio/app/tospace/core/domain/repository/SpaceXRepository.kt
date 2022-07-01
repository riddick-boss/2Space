package abandonedstudio.app.tospace.core.domain.repository

import abandonedstudio.app.tospace.core.domain.model.spacex.DetailedLaunch
import abandonedstudio.app.tospace.core.domain.model.spacex.PastSpaceXLaunch
import abandonedstudio.app.tospace.core.domain.model.spacex.UpcomingSpaceXLaunch
import abandonedstudio.app.tospace.core.domain.util.DefaultPagingSource

interface SpaceXRepository {

    suspend fun getNextLaunch(): DetailedLaunch

    suspend fun getLastLaunch(): DetailedLaunch

    suspend fun loadUpcomingLaunches(page: Int, limit: Int): DefaultPagingSource.Page<UpcomingSpaceXLaunch>

    suspend fun loadPastLaunches(page: Int, limit: Int): DefaultPagingSource.Page<PastSpaceXLaunch>
}