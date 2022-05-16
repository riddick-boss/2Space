package abandonedstudio.app.tospace.core.data.remote.spacex.ktor

object Routes {

    private const val BASE_URL = "https://api.spacexdata.com/v5/"
    private const val LAUNCHES = "${BASE_URL}launches/"

    const val QUERY = "${LAUNCHES}query"
}