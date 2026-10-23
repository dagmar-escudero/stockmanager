package com.stockmanager.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/** DAO del movimiento: CRUD + consultas optimizadas para la sincronización. */
@Dao
interface MovimientoDao {

    @Insert                                             // CREATE
    suspend fun insertar(movimiento: MovimientoInventario): Long

    @Query("SELECT * FROM movimientos ORDER BY fecha DESC")   // READ
    fun obtenerTodos(): Flow<List<MovimientoInventario>>

    // Optimización: trae SOLO los pendientes, no toda la tabla.
    @Query("SELECT * FROM movimientos WHERE sincronizado = 0 ORDER BY fecha")
    suspend fun obtenerPendientes(): List<MovimientoInventario>

    @Query("UPDATE movimientos SET sincronizado = 1 WHERE id IN (:ids)") // UPDATE masivo
    suspend fun marcarSincronizados(ids: List<Long>)

    @Delete                                             // DELETE
    suspend fun eliminar(movimiento: MovimientoInventario)

    // Registra el movimiento y actualiza el stock en una sola transacción (integridad).
    @Transaction
    suspend fun registrar(mov: MovimientoInventario, dao: ProductoDao) {
        insertar(mov)
        val p = dao.obtenerPorCodigo(mov.productoCodigo) ?: return
        val nueva = if (mov.tipo == "ENTRADA") p.cantidadActual + mov.cantidad
                    else p.cantidadActual - mov.cantidad
        dao.actualizar(p.copy(cantidadActual = nueva))
    }
}
