package coelho.caique.clima.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import coelho.caique.clima.data.db.entity.CURRENT_WEATHER_ID
import coelho.caique.clima.data.db.entity.CurrentWeatherEntry
import coelho.caique.clima.data.db.unitlocalized.ImperialCurrentWeatherEntry
import coelho.caique.clima.data.db.unitlocalized.MetricCurrentWeatherEntry
import coelho.caique.clima.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry

@Dao
interface CurrentWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: CurrentWeatherEntry)

    @Query(value= "SELECT * FROM current_weather WHERE id = $CURRENT_WEATHER_ID")
    fun getWeatherMetric(): LiveData<MetricCurrentWeatherEntry>

    @Query(value= "SELECT * FROM current_weather WHERE id = $CURRENT_WEATHER_ID")
    fun getWeatherImperial(): LiveData<ImperialCurrentWeatherEntry>
}