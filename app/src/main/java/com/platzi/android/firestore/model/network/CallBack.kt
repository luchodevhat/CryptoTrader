package com.platzi.android.firestore.model.network

import java.lang.Exception

interface CallBack<T> {

    fun onSuccess(result: T?) {}
    fun onFailed(exception:Exception) {}


}