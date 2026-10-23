package com.stockmanager.repository

import com.stockmanager.data.*
import kotlinx.coroutines.flow.Flow

/** Capa intermedia: la UI nunca toca los DAO directamente. */
class StockRepository(
    private val productoDao: ProductoDao,
    private val movimientoDao: MovimientoDao
) {
    val productos: Flow<List<Producto>> = productoDao.obtenerTodos()
    val movimientos: Flow<List<MovimientoInventario>> = movimientoDao.obtenerTodos()

    suspend fun registrarEntrada(codigo: String, cantidad: Int) =
        movimientoDao.registrar(MovimientoInventario(productoCodigo = codigo,
            tipo = "ENTRADA", cantidad = cantidad), productoDao)

    suspend fun registrarSalida(codigo: String, cantidad: Int) =
        movimientoDao.registrar(MovimientoInventario(productoCodigo = codigo,
            tipo = "SALIDA", cantidad = cantidad), productoDao)

    /** Al recuperar conexión: envía solo los pendientes y los marca como sincronizados. */
    suspend fun sincronizar(api: ApiServicio) {
        val pendientes = movimientoDao.obtenerPendientes()
        if (pendientes.isEmpty()) return
        if (api.enviar(pendientes)) {
            movimientoDao.marcarSincronizados(pendientes.map { it.id })
        }
    }
}

/** Stub del servicio remoto (API REST del servidor central). */
interface ApiServicio { suspend fun enviar(movimientos: List<MovimientoInventario>): Boolean }
