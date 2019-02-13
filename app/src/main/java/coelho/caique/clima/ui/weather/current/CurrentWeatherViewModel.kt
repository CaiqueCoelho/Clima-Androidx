package coelho.caique.clima.ui.weather.current

import androidx.lifecycle.ViewModel;
import coelho.caique.clima.data.provider.UnitProvider
import coelho.caique.clima.data.repository.ForecastRepository
import coelho.caique.clima.internal.UnitSystem
import coelho.caique.clima.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : ViewModel() {
    private val unitSystem = unitProvider.getUnitSystem()

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred{
        forecastRepository.getCurrentWeather(isMetric)
    }
}
