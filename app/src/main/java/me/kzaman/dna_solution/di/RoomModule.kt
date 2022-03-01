package me.kzaman.dna_solution.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.kzaman.dna_solution.database.AppDatabase
import me.kzaman.dna_solution.database.dao.UserDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "android_mvvm")
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideOrderDAO(database: AppDatabase): UserDao = database.userDao()
}