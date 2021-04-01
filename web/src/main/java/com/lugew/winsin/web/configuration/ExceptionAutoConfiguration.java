package com.lugew.winsin.web.configuration;

import com.lugew.winsin.core.exception.Exception;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @author 夏露桂
 * @since 2021/1/21 11:05
 */

@ConditionalOnMissingBean(ExceptionConfigurationSupporter.class)
public class ExceptionAutoConfiguration {
    @Bean
    public ExceptionConfigurationSupporter defaultExceptionConfigurationSupporter() {
        return new ExceptionConfigurationSupporter() {

            @Override
            public String getMessage(Exception exception) {
                return exception.getKeyValue();
            }
        };
    }
}
