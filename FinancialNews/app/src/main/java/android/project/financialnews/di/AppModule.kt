package android.project.financialnews.di

import android.project.financialnews.data.remote.FinancialNewsAPI
import android.project.financialnews.data.repository.FinancialNewsRepository
import android.project.financialnews.data.repository.FinancialNewsRepositoryImpl
import android.project.financialnews.utilities.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideFinancialNewsAPI() : FinancialNewsAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FinancialNewsAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideFinancialNewRepository(api: FinancialNewsAPI): FinancialNewsRepository {
        return FinancialNewsRepositoryImpl(api)
    }

}