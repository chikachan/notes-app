package tabitabi.picco.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"tabitabi.picco.web"})
public class WebConfiguration {

}
//http://www.luckyryan.com/2013/02/07/migrate-spring-mvc-servlet-xml-to-java-config/
