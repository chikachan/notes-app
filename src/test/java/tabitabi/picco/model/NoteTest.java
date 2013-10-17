package tabitabi.picco.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.joda.time.DateTime;
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
    long dateTime = new DateTime().getMillis();
    note1.setLastModification(new  DateTime(dateTime));
    note2.setLastModification(new  DateTime(dateTime));
    assertTrue("Not equals date 1", note1.equals(note2));
    note1.setLastModification(new  DateTime(dateTime+1));
    assertFalse("Equals date 2", note1.equals(note2));
    
    note1 = new Note();
    note2 = new Note();
    note1.setText("Testst");
    assertFalse("Equals text", note1.equals(note2));
  }
  
  @Test
  public void hashCodeTest(){
    
  }
  
}
