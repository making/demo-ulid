package com.example.demoulid.rest;

import com.example.demoulid.Tweet;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {

    private final StringToUlidConverter stringToUlidConverter;

    public RestConfig(StringToUlidConverter stringToUlidConverter) {
        this.stringToUlidConverter = stringToUlidConverter;
    }

    @Override
    public void configureConversionService(ConfigurableConversionService conversionService) {
        conversionService.addConverter(stringToUlidConverter);
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Tweet.class);
    }
}
