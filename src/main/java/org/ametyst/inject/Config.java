package org.ametyst.inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class Config extends WebMvcConfigurationSupport {
    private PathCustomerMethodArgumentResolver pathCustomerMethodArgumentResolver;

    public Config(@Autowired PathCustomerMethodArgumentResolver pathCustomerMethodArgumentResolver) {
        this.pathCustomerMethodArgumentResolver = pathCustomerMethodArgumentResolver;
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(pathCustomerMethodArgumentResolver);
    }
}
