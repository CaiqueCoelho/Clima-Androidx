package coelho.caique.clima

import android.app.Application
import android.preference.PreferenceManager
import coelho.caique.clima.data.ApixuWeatherApiService
import coelho.caique.clima.data.db.ForecastDatabase
import coelho.caique.clima.data.network.ConnectivityInterceptor
import coelho.caique.clima.data.network.ConnectivityInterceptorImpl
import coelho.caique.clima.data.network.WeatherNetworkDataSource
import coelho.caique.clima.data.network.WeatherNetworkDataSourceImpl
import coelho.caique.clima.data.provider.UnitProvider
import coelho.caique.clima.data.provider.UnitProviderImpl
import coelho.caique.clima.data.repository.ForecastRepository
import coelho.caique.clima.data.repository.ForecastRepositoryImpl
import coelho.caique.clima.ui.weather.current.CurrentWeatherViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ForecastApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy{
        import(androidXModule(this@ForecastApplication))

        bind() from singleton { ForecastDatabase(instance())}
        bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ApixuWeatherApiService(instance())}
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(), instance()) }
        bind<UnitProvider>() with singleton {UnitProviderImpl(instance())}
        bind() from provider { CurrentWeatherViewModelFactory(instance(), instance()) }
    }

    override fun onCreate(){
        super.onCreate()
        AndroidThreeTen.init(this)
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
    }
}