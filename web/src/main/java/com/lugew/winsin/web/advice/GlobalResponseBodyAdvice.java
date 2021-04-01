package com.lugew.winsin.web.advice;

import com.lugew.winsin.core.exception.Exception;
import com.lugew.winsin.web.Standard;
import com.lugew.winsin.web.configuration.ExceptionConfigurationSupporter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局JSON返回值拦截器
 * 注意，当返回值是String不支持，使用时尽量避免String作为返回值
 *
 * @author 夏露桂
 * @since 2021/1/19 18:33
 */
@Slf4j
@RestControllerAdvice(annotations = {Standard.class})
public class GlobalResponseBodyAdvice extends GlobalRestfulResponseBodyAdvice {

    public GlobalResponseBodyAdvice(ExceptionConfigurationSupporter exceptionConfigurationSupporter) {
        super(exceptionConfigurationSupporter);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Object exceptionHandle(Exception e) {
        return super.exceptionHandle(e);
    }

    @ExceptionHandler(java.lang.Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Object exceptionHandle(java.lang.Exception e) {
        return super.exceptionHandle(e);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        return com.lugew.winsin.web.response.R.builder().data(body).build();
    }
}
