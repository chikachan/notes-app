package tabitabi.picco.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.h2.tools.Server;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tabitabi.picco.TestingException;
import tabitabi.picco.model.Note;
import tabitabi.picco.util.UTF8ResourceBundle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/beans.xml"})
public class NoteJPADataServiceTest {
	
	
	@Resource
    private NoteJPADataService service;
	
	@Test
	public void create(){
		assertTrue("Service is null", service != null);
		final String testText = "ABCS";
		Note note = new Note();
		note.setText(testText);
		
		service.create(note);
		Map<String, Object> properties = service.em.getProperties();
		List<Note> all = new ArrayList<>(); 
		all.addAll(service.findAll());
		System.out.println(note.getId());
		
		
		assertEquals("Text is not equals", testText,  all.get(0).getText());
		
	}

	static Server db = null;
	@BeforeClass
	public static void setUp(){
	
	try {
		UTF8ResourceBundle properties = new UTF8ResourceBundle("notes-app", Locale.getDefault());
		Class.forName(properties.getString("javax.persistence.jdbc.driver"));
		String dbURL = new StringBuilder(properties.getString(
				"javax.persistence.jdbc.url").replace("IFEXISTS=TRUE", ""))
				.append(String.format("INIT=RUNSCRIPT FROM '%s'\\;",
						ClassLoader.getSystemResource("ddl.sql")))
				.append(String.format("RUNSCRIPT FROM '%s';",
						ClassLoader.getSystemResource("dml.sql")))
				.append("DB_CLOSE_DELAY=-1").toString();

		
		final String[] args = new String[] {
				"-tcpPort", "9090",
				"-tcpAllowOthers","true" };
				 
				 db = org.h2.tools.Server.createTcpServer(args).start();
		
		Connection connection = DriverManager.getConnection(dbURL);
		Statement stmt = connection.createStatement();
		stmt.execute("SELECT 1");

	} catch (Exception exc) {
		throw new TestingException(exc);
	}
}

	
	@AfterClass
	public static void f(){
		if(db != null){
			db.stop();
		}
		
	}
	
}
//jdbc:h2:mem:notes-app-test;MODE=MySQL;INIT=RUNSCRIPT FROM 'classpath:ddl.sql'\;RUNSCRIPT FROM 'classpath:dml.sql';DB_CLOSE_DELAY=-1
