package com.stockmanager.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/** Entidad que representa un producto del inventario. */
@Entity(
    tableName = "productos",
    indices = [Index(value = ["codigo"], unique = true)] // optimiza las búsquedas por código
)
data class Producto(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val codigo: String,
    val nombre: String,
    val cantidadActual: Int = 0
)
