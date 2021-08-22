package com.example.watcha.model

//UI단에서 동일한 인터페이스를 제공한다. load, success, fail
sealed class Resource<out T> {
    object Loading: Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure(val msg: String?) : Resource<Nothing>()
}