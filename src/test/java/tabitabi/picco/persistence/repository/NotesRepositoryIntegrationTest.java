package tabitabi.picco.persistence.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import tabitabi.picco.config.JPAConfiguration;
import tabitabi.picco.model.Note2;
import tabitabi.picco.util.Utils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JPAConfiguration.class })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class NotesRepositoryIntegrationTest {

	@Autowired
	NotesRepository repository;

	@Test
	public void thatDeletingNotesWorks(){
		Note2 note = new Note2();
		note.setLastModification(new Date());
		note.setText("Test delete");
		repository.save(note);
		repository.delete(note.getId());
		
		Iterator<Note2> iterator = repository.findAll().iterator();
		while(iterator.hasNext()){
			assertFalse("Note wasn't deleted",iterator.next().equals(note));
		}
	}

	
	@Test
	public void thatNotesRepositoryWorks() {
		final int numberOfItems = 7;
		final int minRange = 0;
		final int maxRange = 0;
		List<Note2> notes = new ArrayList<>();
		for (int i = 0; i < numberOfItems; i++) {
			Note2 item = new Note2();
			Calendar receivingDate = Calendar.getInstance();
			receivingDate.add(Calendar.DAY_OF_YEAR,
					Utils.randInt(minRange, maxRange));
			item.setReceivingDate(receivingDate.getTime());
			item.setText("TEST-text" + i + receivingDate.toString());
			item.setLastModification(new Date());
			notes.add(repository.save(item));
		}
		
		Iterator<Note2> iterator = repository.findAll().iterator();
		int foundItems = 0;
		while(iterator.hasNext()){
			assertTrue("Notes doesn'T match",notes.contains(iterator.next()));
			foundItems++;
		}
		
		assertEquals("The number of notes doesn't match", foundItems,
				notes.size());
		
	}
	
	
}
