package com.lugew.winsin.web.advice;

import com.lugew.winsin.core.Error;
import com.lugew.winsin.core.exception.Exception;
import com.lugew.winsin.web.Standard;
import com.lugew.winsin.web.configuration.ExceptionConfigurationSupporter;
import com.lugew.winsin.web.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    public Object exceptionHandle(Exception e) {
        return R.builder()
                .code(e.getKey())
                .message(getExceptionConfigurationSupporter().getMessage(e))
                .build();
    }

    @ExceptionHandler(java.lang.Exception.class)
    public Object exceptionHandle(java.lang.Exception e) {
        return R.builder()
                .code(Error.INTERNAL_SERVER_ERROR.getCode())
                .data(e.getMessage())
                .message(Error.INTERNAL_SERVER_ERROR.getValue())
                .build();
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        return com.lugew.winsin.web.response.R.builder().data(body).build();
    }
}
