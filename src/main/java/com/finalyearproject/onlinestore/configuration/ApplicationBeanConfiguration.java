package com.mitron.onlinestore.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mitron.onlinestore.util.mappings.MappingsInitializer;

@Configuration
public class ApplicationBeanConfiguration {

    static ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        MappingsInitializer.initMappings(modelMapper);
    }

    @Bean
    public ModelMapper modelMapper() {
        return modelMapper;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
