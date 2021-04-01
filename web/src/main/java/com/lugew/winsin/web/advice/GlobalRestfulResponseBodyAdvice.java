package com.lugew.winsin.web.advice;

import com.lugew.winsin.core.Error;
import com.lugew.winsin.core.exception.Exception;
import com.lugew.winsin.web.Standard;
import com.lugew.winsin.web.configuration.ExceptionConfigurationSupporter;
import com.lugew.winsin.web.response.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 全局RESTful JSON返回值拦截器
 * 注意，当返回值是String不支持，使用时尽量避免String作为返回值
 *
 * @author 夏露桂
 * @since 2021/1/19 18:33
 */
@Slf4j
@RestControllerAdvice(annotations = {Standard.class})
@RequiredArgsConstructor
public class GlobalRestfulResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private final ExceptionConfigurationSupporter exceptionConfigurationSupporter;

    @ExceptionHandler(Exception.class)
    @ResponseStatus
    public Object exceptionHandle(Exception e) {
        return R.builder()
                .code(e.getKey())
                .message(exceptionConfigurationSupporter.getMessage(e))
                .build();
    }

    @ExceptionHandler(java.lang.Exception.class)
    @ResponseStatus
    public Object exceptionHandle(java.lang.Exception e) {
        return R.builder()
                .code(Error.INTERNAL_SERVER_ERROR.getCode())
                .data(e.getMessage())
                .message(Error.INTERNAL_SERVER_ERROR.getValue())
                .build();
    }

    /**
     * 对R/ResponseEntity类型返回值不做处理
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return isNotR(returnType) && isNotResponseEntity(returnType);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        return body;
    }

    public boolean isResponseEntity(MethodParameter returnType) {
        return "org.springframework.http.ResponseEntity".equals(returnType.getParameterType().getName());
    }

    private boolean isNotResponseEntity(MethodParameter returnType) {
        return !isResponseEntity(returnType);
    }

    public boolean isVoid(MethodParameter returnType) {
        return "java.lang.Void".equals(returnType.getParameterType().getName());
    }

    private boolean notJSON(MediaType selectedContentType) {
        return !selectedContentType.includes(MediaType.APPLICATION_JSON);
    }

    private boolean isR(MethodParameter returnType) {
        return "com.lugew.winsin.web.response.R".equals(returnType.getParameterType().getName());
    }

    private boolean isNotR(MethodParameter returnType) {
        return !isR(returnType);
    }

    public ExceptionConfigurationSupporter getExceptionConfigurationSupporter() {
        return exceptionConfigurationSupporter;
    }
}
