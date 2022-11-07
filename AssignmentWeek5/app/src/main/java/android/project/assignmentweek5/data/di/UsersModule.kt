package android.project.assignmentweek5.data.di

import android.project.assignmentweek5.data.local.database.AppDatabase
import android.project.assignmentweek5.data.remote.api.endpoints.UsersAPI
import android.project.assignmentweek5.data.repository.users.UsersRepository
import android.project.assignmentweek5.data.repository.users.UsersRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class UsersModule {


    @Provides
    fun provideApiService(retrofit: Retrofit) : UsersAPI {
        return retrofit.create(UsersAPI::class.java)
    }

    @Provides
    fun provideUserRepository(apiService: UsersAPI, appDatabase: AppDatabase) : UsersRepository {
        return UsersRepositoryImpl(apiService, appDatabase)
    }

}