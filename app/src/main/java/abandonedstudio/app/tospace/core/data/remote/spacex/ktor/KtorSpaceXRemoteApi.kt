package abandonedstudio.app.tospace.core.data.remote.spacex.ktor

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.data.remote.spacex.SpaceXRemoteApi
import abandonedstudio.app.tospace.core.data.remote.spacex.dto.response.PastSpaceXLaunchesResponse
import abandonedstudio.app.tospace.core.data.remote.spacex.dto.response.SpaceXDetailedLaunchResponse
import abandonedstudio.app.tospace.core.data.remote.spacex.dto.response.UpcomingSpaceXLaunchesResponse
import abandonedstudio.app.tospace.di.ToSpaceApplication
import androidx.annotation.RawRes
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import javax.inject.Inject

class KtorSpaceXRemoteApi @Inject constructor(
    private val client: HttpClient
) : SpaceXRemoteApi {

    override suspend fun getNextLaunch(): SpaceXDetailedLaunchResponse {
        val query = ToSpaceApplication.resources.openRawResource(R.raw.next_spacex_launch_query).bufferedReader().use { it.readText() }
        val body = Json.parseToJsonElement(query)
        return client.post(
            Routes.QUERY
        ) {
            contentType(ContentType.Application.Json)
            setBody(body)
        }.body()
    }

    override suspend fun getLastLaunch(): SpaceXDetailedLaunchResponse {
        val query = ToSpaceApplication.resources.openRawResource(R.raw.last_spacex_launch_query).bufferedReader().use { it.readText() }
        val body = Json.parseToJsonElement(query)
        return client.post(
            Routes.QUERY
        ) {
            contentType(ContentType.Application.Json)
            setBody(body)
        }.body()
    }

    override suspend fun loadUpcomingLaunches(page: Int, limit: Int): UpcomingSpaceXLaunchesResponse {
        val query = paginationQuery(page, limit, R.raw.upcoming_spacex_launches_query_pagination)
        val body = Json.parseToJsonElement(query)
        return client.post(
            Routes.QUERY
        ) {
            contentType(ContentType.Application.Json)
            setBody(body)
        }.body()
    }

    override suspend fun loadPastLaunches(page: Int, limit: Int): PastSpaceXLaunchesResponse {
        val query = paginationQuery(page, limit, R.raw.past_spacex_launches_query_pagination)
        val body = Json.parseToJsonElement(query)
        return client.post(
            Routes.QUERY
        ) {
            contentType(ContentType.Application.Json)
            setBody(body)
        }.body()
    }

    private fun paginationQuery(page: Int, limit: Int, @RawRes queryResID: Int): String  =
        ToSpaceApplication.resources.openRawResource(queryResID).bufferedReader().use { it.readText() }
            .replace("\"page\": 1", "\"page\": $page")
            .replace("\"limit\": 1", "\"limit\": $limit")
}