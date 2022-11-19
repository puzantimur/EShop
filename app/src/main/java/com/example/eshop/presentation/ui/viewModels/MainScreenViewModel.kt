package com.example.eshop.presentation.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.eshop.domain.model.Category
import com.example.domain.eshop.domain.model.Shop
import com.example.domain.eshop.domain.repository.Repository
import com.example.eshop.R
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
            val categoryList = repository.getCategories(listOf(
                Category(R.string.phones, R.drawable.ic_iphone, false),
                Category(R.string.computer, R.drawable.ic_baseline_computer_24, false),
                Category(R.string.health, R.drawable.ic_outline_health_and_safety_24, false),
                Category(R.string.books, R.drawable.ic_baseline_menu_book_24, false),
                Category(R.string.sport, R.drawable.ic_outline_sports_football_24, false),
                Category(R.string.furniture, R.drawable.ic_outline_bed_24, false),
            ))
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
