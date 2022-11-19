package com.example.eshop.presentation.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eshop.domain.model.Cart
import com.example.eshop.domain.repository.Repository
import com.example.eshop.presentation.ui.utils.Lce
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel(private val repository: Repository) : ViewModel() {

    private val _cartFlow = MutableStateFlow<Lce<Cart>>(Lce.Loading)

    val cartFlow: Flow<Lce<Cart>> = _cartFlow.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getCart().onSuccess {
                _cartFlow.value = Lce.Content(it)
            }.onFailure {
                _cartFlow.value = Lce.Error(it)
            }

        }
    }
}