package com.stockmanager.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stockmanager.repository.StockRepository
import kotlinx.coroutines.launch

class InventarioViewModel(private val repo: StockRepository) : ViewModel() {

    val productos = repo.productos

    fun registrarEntrada(codigo: String, cantidad: Int) = viewModelScope.launch {
        repo.registrarEntrada(codigo, cantidad)
    }

    fun registrarSalida(codigo: String, cantidad: Int) = viewModelScope.launch {
        repo.registrarSalida(codigo, cantidad)
    }
}
