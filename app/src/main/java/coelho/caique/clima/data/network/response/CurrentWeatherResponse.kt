package coelho.caique.clima.data.network.response

import coelho.caique.clima.data.db.entity.CurrentWeatherEntry
import coelho.caique.clima.data.db.entity.Location
import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    @SerializedName("location")
    val location: Location
)