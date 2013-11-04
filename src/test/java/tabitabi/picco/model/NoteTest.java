package tabitabi.picco.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashSet;

import org.junit.Test;


public class NoteTest {

  @Test
  public void equalsTest(){
    Note note1 = new Note();
    Note note2 = new Note();
    
    assertTrue("Not reflexive", note1.equals(note1));
    
    assertTrue(note1.equals(note2));
    assertTrue("Not symetric", note1.equals(note2) && note2.equals(note1));
    
    Note note3 = new Note();
    assertTrue(note1.equals(note3));
    assertTrue(note1.equals(note2));
    assertTrue("Not transitive", note1.equals(note2) && note2.equals(note3)
        && note1.equals(note3));
    
    note1 = new Note();
    note2 = new Note();
    assertTrue("Not consistent", note1.equals(note2) && note1.equals(note2)
        && note1.equals(note2));
    
    note1 = new Note();
    assertFalse("equals to null", note1.equals(null));
    
    note1 = new Note();
    note2 = new Note();
    long dateTime = new Date().getTime();
    note1.setLastModification(new  Date(dateTime));
    note2.setLastModification(new  Date(dateTime));
    assertTrue("Not equals date 1", note1.equals(note2));
    note1.setLastModification(new  Date(dateTime+1));
    assertFalse("Equals date 2", note1.equals(note2));
    
    note1 = new Note();
    note2 = new Note();
    note1.setText("Testst");
    assertFalse("Equals text", note1.equals(note2));
    
    note1 = new Note();
    note2 = new Note();
    note1.setReceivingDate(new Date());
    assertFalse("Equals Receiving Date", note1.equals(note2));
  }
  
  @Test
  public void hashCodeTest(){
	  String text = "text";
	  Date rec = new Date();
	  Date mod = new Date();
	  
	  Note note1 = new Note();
	  note1.setText(text);
	  note1.setLastModification(mod);
	  note1.setReceivingDate(rec);
	  
	  Note note2 = new Note();
	  note2.setText(text);
	  note2.setLastModification(mod);
	  assertFalse("Hash code without receiving date is equals",
				note1.hashCode() == note2.hashCode());
	  note2.setReceivingDate(rec);
		assertTrue("Hash code is not equals",
				note1.hashCode() == note2.hashCode());
		
	  HashSet<Note> notes = new HashSet<>();
	  notes.add(note1);
	  notes.add(note1);
	  assertTrue("Size is one", notes.size() == 1);
	  notes.add(note2);
	  assertTrue("Size is one with note2", notes.size() == 1);
	  note2.setText("");
	  notes.add(note2);
	  assertTrue("Size is two", notes.size() == 2);
  }
  
}
