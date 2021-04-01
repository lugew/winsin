package com.lugew.winsin.web.configuration;

import com.lugew.winsin.web.advice.GlobalRestfulResponseBodyAdvice;
import org.springframework.context.annotation.Bean;

/**
 * 标准RESTful响应配置
 *
 * @author 夏露桂
 * @since 2021/1/19 19:11
 */
public class RestfulRConfiguration {
    @Bean
    public GlobalRestfulResponseBodyAdvice globalRestfulResponseBodyAdvice(
            @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
                    ExceptionConfigurationSupporter exceptionConfigurationSupporter) {
        return new GlobalRestfulResponseBodyAdvice(exceptionConfigurationSupporter);
    }
}
