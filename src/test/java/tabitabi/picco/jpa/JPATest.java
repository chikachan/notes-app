package tabitabi.picco.jpa;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tabitabi.picco.TestingException;
import tabitabi.picco.model.Note;
import tabitabi.picco.util.UTF8ResourceBundle;

public class JPATest {

	private static final UTF8ResourceBundle properties = new UTF8ResourceBundle(
			"notes-app-TEST", Locale.getDefault());
	private static EntityManagerFactory emFactory = null;

	@BeforeClass
	public static void setUp() {
		try {
			setUpDB();
			setUpEntityManager();
		} catch (Exception exc) {
			throw new TestingException(exc);
		}
	}

	@AfterClass
	public static void tearDown() {
		if (emFactory != null) {
			emFactory.close();
		}
	}

	private static void setUpDB() {
		try {

			Class.forName(properties.getString("javax.persistence.jdbc.driver"));
			String dbURL = new StringBuilder(properties.getString(
					"javax.persistence.jdbc.url").replace("IFEXISTS=TRUE", ""))
					.append(String.format("INIT=RUNSCRIPT FROM '%s'\\;",
							ClassLoader.getSystemResource("ddl.sql")))
					.append(String.format("RUNSCRIPT FROM '%s';",
							ClassLoader.getSystemResource("dml.sql")))
					.append("DB_CLOSE_DELAY=-1").toString();

			Connection connection = DriverManager.getConnection(dbURL);
			Statement stmt = connection.createStatement();
			stmt.execute("SELECT 1");

		} catch (Exception exc) {
			throw new TestingException(exc);
		}
	}

	private static void setUpEntityManager() {
		emFactory = Persistence.createEntityManagerFactory("notes-app",
				properties.asMap());
	}

	@Test
	public void test() {
		final String testText = "ABCS";
		Note n = new Note();
		n.setText(testText);
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("DELETE FROM note").executeUpdate();
		em.persist(n);
		em.flush();
		em.getTransaction().commit();
		em.close();
		
		em = emFactory.createEntityManager();
		Query query = em.createNativeQuery("SELECT * FROM note LIMIT 1",Note.class);
		Note singleResult = (Note) query.getSingleResult();
		System.out.println(singleResult.getText());
		System.out.println(testText);
		assertEquals("Text is not equals", testText,  singleResult.getText() );
		
	}

}
