package com.reqres.test.config;

import com.reqres.test.automation.scenario.ScenarioGenerator;
import com.restApp.rest.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:test.properties"})
public class RestItConfig {


    @Bean
    public ScenarioGenerator scenarioGenerator() {
        return new ScenarioGenerator(ScenarioGenerator.ExecutionType.REST);
    }

    @Bean
    public RestConfig restConfig() {
        return new RestConfig();
    }


    @Bean
    public RestPostTaks restPostTaks() {
        return new RestPostTaks();
    }

    @Bean
    public CommonAnnotationBeanPostProcessor commonAnnotationBeanPostProcessor() {
        return new CommonAnnotationBeanPostProcessor();
    }

    @Bean
    public Post post() {
        return new Post();
    }

}
