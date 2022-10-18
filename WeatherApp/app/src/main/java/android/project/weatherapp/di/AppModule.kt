package android.project.weatherapp.di

import android.app.Application
import android.project.weatherapp.data.local.DefaultLocationTracker
import android.project.weatherapp.data.remote.WeatherAppAPI
import android.project.weatherapp.data.remote.interceptor.WeatherInterceptor
import android.project.weatherapp.data.repository.WeatherAppRepository
import android.project.weatherapp.data.repository.WeatherAppRepositoryImpl
import android.project.weatherapp.utilities.Constants
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideWeatherAppAPI(
        okHttp: OkHttpClient
        ) : WeatherAppAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp)
            .build()
            .create(WeatherAppAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherAppRepository(api: WeatherAppAPI): WeatherAppRepository {
        return WeatherAppRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideOkHttp(
        interceptor: WeatherInterceptor
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()


    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(): WeatherInterceptor = WeatherInterceptor()


}
