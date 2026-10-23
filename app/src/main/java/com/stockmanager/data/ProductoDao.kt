package com.stockmanager.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/** DAO del producto: operaciones CRUD. */
@Dao
interface ProductoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)   // CREATE
    suspend fun insertar(producto: Producto): Long

    @Query("SELECT * FROM productos ORDER BY nombre")  // READ (reactivo con Flow)
    fun obtenerTodos(): Flow<List<Producto>>

    @Query("SELECT * FROM productos WHERE codigo = :codigo LIMIT 1")
    suspend fun obtenerPorCodigo(codigo: String): Producto?

    @Update                                             // UPDATE
    suspend fun actualizar(producto: Producto)

    @Delete                                             // DELETE
    suspend fun eliminar(producto: Producto)
}
