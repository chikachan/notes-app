package tabitabi.picco.persistence.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import tabitabi.picco.config.JPATestConfiguration;
import tabitabi.picco.model.Account;
import tabitabi.picco.model.Note2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JPATestConfiguration.class })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AccountsRepositoryIntegrationTest {

	@Autowired
	NotesRepository notesRepository;
	@Autowired
	AccountsRepository repository;

	@Test
	public void retrievingByEmail(){
		final String email = "EMAIL@mail.com";
		Account account = new Account();
		account.setEmail(email);
		
		repository.save(account);
		
		Account detachedAccount = new Account(repository.findByEmail(email));
		assertEquals("Account retrieved by email doesn't match", account,
				detachedAccount);
	}
	
	
	@Test
	public void thatAccountsRepositoryWorks() {
		final int numberOfItems = 7;
		List<Account> accounts = new ArrayList<>();
		for (int i = 0; i < numberOfItems; i++) {
			Account item = new Account();
			item.setEmail("jdoe"+i+"@mail.com");
			accounts.add(repository.save(item));
		}		

		/* Accounts are found and retrieved */
		int foundCount = 0;
		Iterator<Account> foundItems = repository.findAll().iterator();
		while (foundItems.hasNext()) {
			Account next = new Account(foundItems.next()); //create a detached entity  
			assertTrue("Account not found in collection",
					accounts.contains(next));
			foundCount++;
		}
		
		/* Number of accounts found matches */
		assertEquals(
				"The number of items in the collection and the number of items retrieved from the repository doesn't match",
				accounts.size(), foundCount);
		
		/* Accounts are not equals */
		foundCount = 0;
		foundItems = repository.findAll().iterator();
		while (foundItems.hasNext()) {
			Account next = new Account(foundItems.next()); //create a detached entity
			if(foundCount % 2 == 0 ){
				next.setEmail("");
			}else{
				next.setId(new Random().nextLong());
			}
			assertFalse("Account found in collection",
					accounts.contains(next));
			foundCount++;
		}	
		
	}

	@Test
	public void thatRelationAccountNotesWorks() {
		Account account = new Account();
		account.setEmail("RelationAccountNotesWorks@mail.com");
		repository.save(account);
		
		final String text = "test Text acc";
		final String text2 = "22test Text acc";
		final Date date = new Date();
		Note2 note = new Note2();
		note.setText(text);
		note.setReceivingDate(date);
		note.setAccountId(account.getId());
		Note2 note2 = new Note2();		
		note2.setText(text2);
		note2.setReceivingDate(date);
		note2.setAccountId(account.getId());
		notesRepository.save(note);
		notesRepository.save(note2);
		
		Set<Long> ids = new HashSet<>();
		ids.add(note.getId());
		ids.add(note2.getId());
		account.setNotesIds(ids);
		
		Account retrievedAccount = new Account(repository.findOne(account
				.getId()));
		Set<Long> notesIds = retrievedAccount.getNotesIds();
		assertEquals("The number of notes in the account doesn't match",
				notesIds.size(), 2);
		assertTrue("Account doesn't contain NoteId 1",
				notesIds.contains(note.getId()));
		assertTrue("Account doesn't contain NoteId 2",
				notesIds.contains(note2.getId()));
		
		Set<Note2> notesByAccountId = notesRepository.findByAccountId(account
				.getId());
		assertEquals("Not the expected number of notes by accountId", 2,
				notesByAccountId.size());
		assertTrue("Note by accountId not found",notesByAccountId.contains(note));
		assertTrue("Note2 by accountId not found",notesByAccountId.contains(note2));
	}
	

}
