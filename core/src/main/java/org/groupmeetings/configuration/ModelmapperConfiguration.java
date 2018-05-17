package org.groupmeetings.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelmapperConfiguration {
 @Bean
 ModelMapper getModelMapper() {
	 return new ModelMapper();
 }
}
