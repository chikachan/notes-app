package tabitabi.picco.jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Locale;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tabitabi.picco.util.UTF8ResourceBundle;

//http://java.dzone.com/articles/spring-3-makes-use-embedded-easy
//http://docs.spring.io/spring/docs/3.2.4.RELEASE/spring-framework-reference/html/orm.html#orm-jpa
//http://www.vogella.com/articles/JavaPersistenceAPI/article.html
//http://spring.io/guides/gs/accessing-data-jpa/

public class JPATest {

	private static final UTF8ResourceBundle properties = new UTF8ResourceBundle(
			"notes-app-TEST", Locale.getDefault());
	private static EntityManagerFactory emFactory= null;

	@BeforeClass
	public static void setUp() {
		setUpDB();
		setUpEntityManager();
	}
	
	@AfterClass
	public static void tearDown(){	
		if(emFactory != null){
			emFactory.close();
		}
	}

	private static void setUpDB() {
		try {

			Class.forName(properties.getString("db.driver"));
			String dbURL = new StringBuilder(properties.getString("db.url")
					.replace("IFEXISTS=TRUE", ""))
					.append(String.format("INIT=RUNSCRIPT FROM '%s'\\;",
							ClassLoader.getSystemResource("ddl.sql")))
					.append(String.format("RUNSCRIPT FROM '%s'",
							ClassLoader.getSystemResource("dml.sql")))
					.toString();

			Connection connection = DriverManager.getConnection(dbURL);
			Statement stmt = connection.createStatement();
			stmt.execute("SELECT 1");

		} catch (Exception exc) {
			throw new TestingException(exc);
		}
	}

	private static void setUpEntityManager() {		
		 emFactory = Persistence.createEntityManagerFactory(
				properties.getString("persistence-unit-name"),
				properties.asMap());	
	}

	@Test
	public void test() {
		//emFactory.createEntityManager();
		Map<String, Object> properties2 = emFactory.getProperties();
		properties2.size();
	}

}
