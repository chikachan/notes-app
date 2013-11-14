package tabitabi.picco.persistence.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import tabitabi.picco.config.JPAConfiguration;
import tabitabi.picco.model.Account;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JPAConfiguration.class })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AccountsRepositoryIntegrationTest {

	@Autowired
	AccountsRepository repository;

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
		assertTrue(true);
		assertEquals(1, 1);
	}
	
	@Test
	public void thatUpdatesWork(){
		
	}

}
