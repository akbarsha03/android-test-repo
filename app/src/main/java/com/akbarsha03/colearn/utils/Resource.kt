package com.akbarsha03.colearn.utils

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

class Resource<out T>(val status: Status?, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String?, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}

inline fun <reified T> Resource<T>.onSuccess(block: (value: T?) -> Unit): Resource<T> {
    if (this.status == Status.SUCCESS) block(this.data)
    return this
}

inline fun <reified T> Resource<T>.onFailure(block: (value: T?) -> Unit): Resource<T> {
    if (this.status == Status.ERROR) block(this.data)
    return this
}

inline fun <reified T> Resource<T>.onLoading(block: (value: T?) -> Unit): Resource<T> {
    if (this.status == Status.LOADING) block(this.data)
    return this
}