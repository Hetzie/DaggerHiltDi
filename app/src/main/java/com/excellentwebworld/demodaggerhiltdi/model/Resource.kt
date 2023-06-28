package com.excellentwebworld.demodaggerhiltdi.model

import android.os.Message

sealed class Resource<T>(val data: T?, val msg: String?) {
    class Error<T>(message: String?): Resource<T>(null, message){}
    class Success<T>(data: T?): Resource<T>(data, null){}
}