package com.brandjunhoe.shippingservice.common.exception

import com.brandjunhoe.shippingservice.common.code.ErrorCode

open class DataNotFoundException : RuntimeException() {
    val errorCode: ErrorCode = ErrorCode.DATA_NOT_FOUND
}