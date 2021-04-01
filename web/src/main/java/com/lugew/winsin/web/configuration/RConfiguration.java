package com.lugew.winsin.web.configuration;

import com.lugew.winsin.web.advice.GlobalResponseBodyAdvice;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;

/**
 * 标准响应配置
 *
 * @author 夏露桂
 * @since 2021/1/19 19:11
 */
@AutoConfigureAfter(ExceptionConfigurationSupporter.class)
public class RConfiguration {
    @Bean
    public GlobalResponseBodyAdvice globalResponseBodyAdvice(
            ExceptionConfigurationSupporter exceptionConfigurationSupporter) {
        return new GlobalResponseBodyAdvice(exceptionConfigurationSupporter);
    }
}
