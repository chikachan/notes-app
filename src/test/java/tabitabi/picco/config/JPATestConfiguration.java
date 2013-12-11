package tabitabi.picco.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import tabitabi.picco.config.JPAConfiguration;
import tabitabi.picco.persistence.repository.AccountsRepository;
import tabitabi.picco.persistence.repository.NotesRepository;

@Configuration
@EnableJpaRepositories(basePackages = "tabitabi.picco.persistence.repository", 
	includeFilters = @ComponentScan.Filter(value = { NotesRepository.class, 
			AccountsRepository.class }, 
	type = FilterType.ASSIGNABLE_TYPE))
@EnableTransactionManagement
public class JPATestConfiguration extends JPAConfiguration {
	
	@Override
	public DataSource dataSource() throws SQLException {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.build();
	}
	

}
