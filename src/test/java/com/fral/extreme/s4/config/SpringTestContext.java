package com.fral.extreme.s4.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.fral.extreme.s4.services"})
public class SpringTestContext {
}