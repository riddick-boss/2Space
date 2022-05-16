package abandonedstudio.app.tospace.core.data.remote.spacex.dto.query

object LaunchQuery {
    const val LAST =
        "{\"query\":{\"upcoming\":false},\"options\":{\"limit\":1,\"sort\":{\"flight_number\":\"desc\"},\"populate\":[{\"path\":\"cores\"},{\"path\":\"launchpad\"},{\"path\":\"rocket\",\"select\":{\"name\":1}},{\"path\":\"fairings\"},{\"path\":\"capsules\"},{\"path\":\"payloads\"},{\"path\":\"crew\",\"populate\":[{\"path\":\"crew\"}]},{\"path\":\"cores\",\"populate\":[{\"path\":\"core\"},{\"path\":\"landpad\"}]}]}}"

    const val NEXT =
        "{\"query\":{\"upcoming\":true},\"options\":{\"limit\":1,\"sort\":{\"flight_number\":\"asc\"},\"populate\":[{\"path\":\"cores\"},{\"path\":\"launchpad\"},{\"path\":\"rocket\",\"select\":{\"name\":1}},{\"path\":\"fairings\"},{\"path\":\"capsules\"},{\"path\":\"payloads\"},{\"path\":\"crew\",\"populate\":[{\"path\":\"crew\"}]},{\"path\":\"cores\",\"populate\":[{\"path\":\"core\"},{\"path\":\"landpad\"}]}]}}"
}