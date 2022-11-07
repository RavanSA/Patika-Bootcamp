package android.project.assignmentweek5.data.di

import android.project.assignmentweek5.data.local.database.AppDatabase
import android.project.assignmentweek5.data.remote.api.endpoints.PostsAPI
import android.project.assignmentweek5.data.repository.posts.PostsRepository
import android.project.assignmentweek5.data.repository.posts.PostsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class PostsModule {


    @Provides
    fun provideApiService(retrofit: Retrofit) : PostsAPI {
        return retrofit.create(PostsAPI::class.java)
    }

    @Provides
    fun providePostRepository(apiService: PostsAPI, appDatabase: AppDatabase) : PostsRepository {
        return PostsRepositoryImpl(apiService, appDatabase)
    }

}