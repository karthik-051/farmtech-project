package com.mitron.onlinestore.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mitron.onlinestore.web.interceptors.FaviconInterceptor;
import com.mitron.onlinestore.web.interceptors.GreetingInterceptor;
import com.mitron.onlinestore.web.interceptors.TitleInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final TitleInterceptor titleInterceptor;
    private final FaviconInterceptor faviconInterceptor;
    private final GreetingInterceptor greetingInterceptor;

    @Autowired
    public WebMvcConfiguration(TitleInterceptor titleInterceptor, FaviconInterceptor faviconInterceptor, GreetingInterceptor greetingInterceptor) {
        this.titleInterceptor = titleInterceptor;
        this.faviconInterceptor = faviconInterceptor;
        this.greetingInterceptor = greetingInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.titleInterceptor);
        registry.addInterceptor(this.faviconInterceptor);
        registry.addInterceptor(this.greetingInterceptor);
    }
}
