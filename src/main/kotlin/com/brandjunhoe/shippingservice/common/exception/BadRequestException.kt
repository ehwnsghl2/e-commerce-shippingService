package com.brandjunhoe.shippingservice.common.exception

import com.brandjunhoe.shippingservice.common.code.ErrorCode


open class BadRequestException(val errorCode: ErrorCode) : RuntimeException()