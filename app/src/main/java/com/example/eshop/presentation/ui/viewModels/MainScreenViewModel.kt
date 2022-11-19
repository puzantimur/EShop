package com.example.eshop.presentation.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eshop.domain.model.Category
import com.example.eshop.domain.model.Shop
import com.example.eshop.domain.repository.Repository
import com.example.eshop.presentation.ui.utils.Lce
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _categoryFlow = MutableStateFlow<List<Category>>(emptyList())

    val categoryFlow: Flow<List<Category>> = _categoryFlow.asStateFlow()

    init {
        viewModelScope.launch {
            val categoryList = repository.getCategories()
            _categoryFlow.value = categoryList
        }
    }

    private val _shopFlow = MutableStateFlow<Lce<Shop>>(Lce.Loading)

    val shopFlow: Flow<Lce<Shop>> = _shopFlow.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getShop().onSuccess {
                _shopFlow.value = Lce.Content(it)
            }.onFailure {
                _shopFlow.value = Lce.Error(it)
            }

        }
    }
}
