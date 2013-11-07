package tabitabi.picco.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Locale;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tabitabi.picco.TestingException;
import tabitabi.picco.model.Note;
import tabitabi.picco.service.NoteJPADataService;
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
		/*
		note = service.create(note);
		System.out.println(note.getId());		
		assertEquals("Text is not equals", testText,  service.find(note.getId()).getText() );
		*/
	}

}
