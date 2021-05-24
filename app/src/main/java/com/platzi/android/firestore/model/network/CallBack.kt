package com.platzi.android.firestore.model.network

import java.lang.Exception
import java.lang.RuntimeException

interface CallBack<T> {

    fun onSuccess(result: T?) {}
    fun onFailed(exception:Exception) {}


}