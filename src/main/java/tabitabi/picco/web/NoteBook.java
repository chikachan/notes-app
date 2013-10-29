package tabitabi.picco.web;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import tabitabi.picco.model.Note;

@ManagedBean
@SessionScoped
public class NoteBook implements Serializable{
  
  private static final long serialVersionUID = 8967712157344853046L;

  public static final String CREATE_NOTE_VIEW = "pm:editNoteView";
  private static final String DEFAULT_DATE_FORMAT = "MMM/dd/yyyy";
  private static final String DEFAULT_LOCALE = "en_US";
  private static final String JAPANESE_DATE_FORMAT = "yyyy/MM/dd";
  private static final String JAPANESE_LOCALE = "ja_JP";
  private final SimpleDateFormat minDateFrmtr = new SimpleDateFormat("MM/dd/yy");
  private static final String MAX_DATE = "12/31/21";
  private Note note = new Note();
  private List<Note> notes = new ArrayList<Note>();

  
  public String getCalendarLocale(){
    final Locale locale = FacesContext.getCurrentInstance().getViewRoot()
        .getLocale();

    if (Locale.JAPANESE.equals(locale)) {
      return JAPANESE_LOCALE;
    }
    
    return DEFAULT_LOCALE;   
  }
  
  public String getDateFormat() {
    final Locale locale = FacesContext.getCurrentInstance().getViewRoot()
        .getLocale();

    if (Locale.JAPANESE.equals(locale)) {
      return JAPANESE_DATE_FORMAT;
    }

    return DEFAULT_DATE_FORMAT;
  }
  
  public String getMaxDate(){
    return MAX_DATE;
  }
  
  public String getMinDate(){
    return minDateFrmtr.format(new Date());
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
