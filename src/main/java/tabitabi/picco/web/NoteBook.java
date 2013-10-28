package tabitabi.picco.web;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tabitabi.picco.model.Note;

@ManagedBean
@SessionScoped
public class NoteBook implements Serializable{
  
  private static final long serialVersionUID = 8967712157344853045L;

  public static final String CREATE_NOTE_VIEW = "pm:editNoteView";
  private final SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yy");
  private static final String MAX_DATE = "12/31/21";
  private Note note = new Note();
  private List<Note> notes = new ArrayList<Note>();

  public String getMaxDate(){
    return MAX_DATE;
  }
  
  public String getMinDate(){
    return dateFormatter.format(new Date());
  }
  
  public Note getNote(){
    return note;
  }
  
  public List<Note> getNotes(){
    return notes;
  }
  
  public String initNew(){
    note = new Note();
    note.setReceivingDate(new Date());
    return CREATE_NOTE_VIEW;
  }
  
  public NoteBook(){
  }
  
  public void save(){
    if (!notes.contains(note)){
      note.setLastModification(new Date());
      notes.add(note);
    }
    note = new Note();
  }
  
  public void setNote(Note note){
    this.note = note;
  }

}
