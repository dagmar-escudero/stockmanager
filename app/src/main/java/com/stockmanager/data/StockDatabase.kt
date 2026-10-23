package com.stockmanager.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/** Base de datos local (SQLite gestionada por Room). Es la que da la persistencia. */
@Database(
    entities = [Producto::class, MovimientoInventario::class],
    version = 1,
    exportSchema = true
)
abstract class StockDatabase : RoomDatabase() {

    abstract fun productoDao(): ProductoDao
    abstract fun movimientoDao(): MovimientoDao

    companion object {
        @Volatile private var INSTANCE: StockDatabase? = null

        fun obtener(context: Context): StockDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    StockDatabase::class.java,
                    "stockmanager.db"
                ).build().also { INSTANCE = it }
            }
    }
}
