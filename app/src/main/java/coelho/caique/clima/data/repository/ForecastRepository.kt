package coelho.caique.clima.data.repository

import androidx.lifecycle.LiveData
import coelho.caique.clima.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
}