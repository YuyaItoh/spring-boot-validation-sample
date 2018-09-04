package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig {
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean() {
            {
                setValidationMessageSource(new ReloadableResourceBundleMessageSource() {
                    {
                        setBasename("classpath:/ValidationMessages");
                        setDefaultEncoding("UTF-8");
                    }
                });
            }
        };
    }
}
