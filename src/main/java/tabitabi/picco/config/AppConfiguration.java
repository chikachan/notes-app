package tabitabi.picco.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import tabitabi.picco.browserid.Verifier;

@Configuration
@Import({ JPAConfiguration.class, WebConfiguration.class })
public class AppConfiguration {
	
	@Bean
	public Verifier verifier(){
		return new Verifier();
	}
	
}
