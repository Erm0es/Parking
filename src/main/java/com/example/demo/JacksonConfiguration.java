package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.geo.GeoModule;

@Configuration
public class JacksonConfiguration {

  @Bean
	GeoModule geolatteModule(){
	return new GeoModule();
	}

}

