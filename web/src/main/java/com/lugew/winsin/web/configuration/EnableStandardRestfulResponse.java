package com.lugew.winsin.web.configuration;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启标准RESTful响应，标准响应不支持{@link String}类型
 *
 * @author 夏露桂
 * @since 2021/1/19 19:10
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({RestfulRConfiguration.class, ExceptionAutoConfiguration.class})
public @interface EnableStandardRestfulResponse {
}
