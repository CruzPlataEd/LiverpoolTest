package com.example.liverpooltest.ui.viewmodel

import android.widget.ArrayAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.liverpooltest.data.model.ApiResponse
import com.example.liverpooltest.data.model.Products
import com.example.liverpooltest.data.model.SortOptions
import com.example.liverpooltest.domain.GetInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val getInfoUseCase: GetInfoUseCase): ViewModel() {

    val products = MutableLiveData<List<Products>>()
    val sortOptions = MutableLiveData<List<String>>()
    var sortUrl = MutableLiveData<List<String>>()
    var sortSelected : String = "Relevancia|0"
    var search : String = ""
    var page : Int = 1
    fun onCreate(){
        viewModelScope.launch {
            val result : ApiResponse = getInfoUseCase.invoke(1,"","")
            if (result.information !=null && result.status != null && result.status.statusCode == 0){
                products.value = emptyList()
                sortOptions.value = emptyList()

                products.postValue(result.information.products)
                var listOptions : ArrayList<String> = ArrayList()
                var listUrl : ArrayList<String> = ArrayList()
                result.information.sortOptions.forEach { sort ->
                    listOptions.add(sort.sortText)
                    listUrl.add(sort.sortUrl)
                }
                sortOptions.postValue(listOptions)
                sortUrl.postValue(listUrl)
            }
        }
    }

    fun updateInfo(){
        viewModelScope.launch {
            val result : ApiResponse = getInfoUseCase.invoke(page,search,sortSelected)
            if (result.information !=null && result.status != null && result.status.statusCode == 0){
                products.value = emptyList()
                products.postValue(result.information.products)
            }
        }
    }

    fun getPageString(): String{
        return page.toString()
    }

    fun getSortRequest(position: Int): String{
        return sortUrl.value?.get(position).toString()
    }

}