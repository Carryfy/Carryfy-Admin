package id.co.admincarryfy.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import id.co.admincarryfy.data.database.AppDatabase
import id.co.admincarryfy.data.database.DatabaseDao
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "db_admin"
    ).allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun provideDao(database: AppDatabase) = database.databaseDao()

}