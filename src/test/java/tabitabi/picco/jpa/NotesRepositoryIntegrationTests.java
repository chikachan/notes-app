package tabitabi.picco.jpa;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import tabitabi.picco.config.JPAConfiguration;
import tabitabi.picco.model.Note2;
import tabitabi.picco.persistence.repository.NotesRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JPAConfiguration.class })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class NotesRepositoryIntegrationTests {

	@Autowired
	NotesRepository notesRepository;

	@Test
	public void thatItemIsInsertedIntoRepoWorks() throws Exception {

		Note2 note = new Note2();
		note.setText("Abcd");

		notesRepository.save(note);

		Iterable<Note2> findAll = notesRepository.findAll();
		Note2 next = findAll.iterator().next();

		assertEquals(next.getText(), note.getText());

	}

}
