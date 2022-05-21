package abandonedstudio.app.tospace.core.data.remote.spacex.ktor

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.data.remote.spacex.SpaceXRemoteApi
import abandonedstudio.app.tospace.core.data.remote.spacex.dto.response.SpaceXSingleLaunchResponse
import abandonedstudio.app.tospace.di.ToSpaceApplication
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import javax.inject.Inject

class KtorSpaceXRemoteApi @Inject constructor(
    private val client: HttpClient
) : SpaceXRemoteApi {

    override suspend fun getNextLaunch(): SpaceXSingleLaunchResponse {
        val query = ToSpaceApplication.resources.openRawResource(R.raw.next_spacex_launch_query).bufferedReader().use { it.readText() }
        val body = Json.parseToJsonElement(query)
        return client.post(
            Routes.QUERY
        ) {
            contentType(ContentType.Application.Json)
            setBody(body)
        }.body()
    }

    override suspend fun getLastLaunch(): SpaceXSingleLaunchResponse {
        val query = ToSpaceApplication.resources.openRawResource(R.raw.last_spacex_launch_query).bufferedReader().use { it.readText() }
        val body = Json.parseToJsonElement(query)
        return client.post(
            Routes.QUERY
        ) {
            contentType(ContentType.Application.Json)
            setBody(body)
        }.body()
    }
}