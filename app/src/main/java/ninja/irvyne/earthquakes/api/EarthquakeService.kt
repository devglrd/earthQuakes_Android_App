package ninja.irvyne.earthquakes.api

import ninja.irvyne.earthquakes.api.model.EarthquakeData
import retrofit2.Call
import retrofit2.http.GET


interface EarthquakeService {
    @GET("earthquakes/feed/v1.0/summary/significant_month.geojson")
    fun listSignificantEarthquakes(): Call<EarthquakeData>

    @GET("earthquakes/feed/v1.0/summary/4.5_month.geojson")
    fun listM4Earthquakes(): Call<EarthquakeData>

    @GET("earthquakes/feed/v1.0/summary/all_month.geojson")
    fun pleaseForgiveMe(): Call<EarthquakeData>
}