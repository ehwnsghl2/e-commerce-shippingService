package com.brandjunhoe.shippingservice.common.handler

import com.brandjunhoe.shippingservice.common.code.ErrorCode
import com.brandjunhoe.shippingservice.common.code.ErrorException
import com.brandjunhoe.shippingservice.common.exception.BadRequestException
import com.brandjunhoe.shippingservice.common.exception.DataNotFoundException
import com.brandjunhoe.shippingservice.common.response.CommonResponse
import org.apache.http.auth.AuthenticationException
import org.springframework.beans.BeanInstantiationException
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.multipart.MaxUploadSizeExceededException
import java.io.IOException
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class GlobalExceptionHandler {

    @ExceptionHandler(
        value = [
            HttpMessageNotReadableException::class,
            HttpRequestMethodNotSupportedException::class,
            MaxUploadSizeExceededException::class,
            BindException::class,
            MethodArgumentNotValidException::class,
            IllegalArgumentException::class,
            BeanInstantiationException::class,
            AuthenticationException::class,
            AccessDeniedException::class,
            javax.websocket.DecodeException::class,
            MethodArgumentTypeMismatchException::class,
            IOException::class,
            Exception::class
        ]
    )
    @Throws(Exception::class)
    fun commonExcetpion(e: Exception, request: HttpServletRequest, webRequest: WebRequest): CommonResponse<Unit> {

        val errorCode = when (e::class.java.simpleName) {
            // validate ??????
            ErrorException.BindException.name,
            ErrorException.MethodArgumentNotValidException.name,
            ErrorException.HttpMessageNotReadableException.name,
            ErrorException.HttpRequestMethodNotSupportedException.name,
            ErrorException.IllegalArgumentException.name,
            ErrorException.FormValidationException.name, // ?????? ??????
            ErrorException.AuthenticationException.name -> ErrorCode.UNAUTHORIZED
            // ?????? ??????
            ErrorException.AccessDeniedException.name -> ErrorCode.FORBIDDEN
            // ?????? ??????
            else -> ErrorCode.INTERNAL_SERVER_ERROR
        }

        return CommonResponse(errorCode.status, errorCode.code, errorCode.message)

    }

    @ExceptionHandler(value = [BadRequestException::class])
    fun BadRequestExcetpion(e: BadRequestException): CommonResponse<Unit> =
        CommonResponse(e.errorCode.status, e.errorCode.code, e.errorCode.message)

    @ExceptionHandler(value = [DataNotFoundException::class])
    fun DataNotFoundExcetpion(e: DataNotFoundException): CommonResponse<Unit> =
        CommonResponse(e.errorCode.status, e.errorCode.code, e.errorCode.message)


}