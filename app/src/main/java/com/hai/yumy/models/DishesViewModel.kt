package com.hai.yumy.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hai.yumy.data.getDishes

class DishesViewModel : ViewModel() {
    private var _dishes = MutableLiveData(listOf<Dish>())
    val dishes: LiveData<List<Dish>> = _dishes

    fun addItem(item: Dish) {
        _dishes.value = _dishes.value!! + listOf(item)
    }

    fun removeItem(item: Dish) {
        _dishes.value = _dishes.value!!.toMutableList().also {
            it.remove(item)
        }
    }

    fun fillDishesList(dishes: List<Dish>) {
        _dishes.value = dishes
    }

    fun filterItems(filter: String) {
        fillDishesList(getDishes())
        _dishes.value = _dishes.value?.filter {
            it.name.contains(filter)
        }
    }

}