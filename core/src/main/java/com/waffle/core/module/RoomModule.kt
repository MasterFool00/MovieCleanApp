package com.waffle.core.module

import androidx.room.Room
import com.waffle.core.local.LocalDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("movieApp".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(androidContext(), LocalDatabase::class.java, "movie_db")
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}