package pl.mateuszmacholl.BCryptPasswordEncoder;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by ggere on 12.02.2018.
 */
@Service
public class MyBCryptPasswordEncoder {
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
