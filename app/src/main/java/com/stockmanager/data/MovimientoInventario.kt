package com.stockmanager.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/** Registro de entrada o salida de mercancía (se guarda aunque no haya internet). */
@Entity(
    tableName = "movimientos",
    foreignKeys = [ForeignKey(
        entity = Producto::class,
        parentColumns = ["codigo"],
        childColumns = ["productoCodigo"],
        onDelete = ForeignKey.CASCADE // integridad referencial
    )],
    indices = [Index("productoCodigo"), Index("sincronizado")]
)
data class MovimientoInventario(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val productoCodigo: String,
    val tipo: String,          // "ENTRADA" o "SALIDA"
    val cantidad: Int,
    val fecha: Long = System.currentTimeMillis(),
    val sincronizado: Boolean = false // false = pendiente de enviar al servidor
)
