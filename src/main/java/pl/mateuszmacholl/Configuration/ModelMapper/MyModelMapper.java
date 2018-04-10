package pl.mateuszmacholl.Configuration.ModelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyModelMapper {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
