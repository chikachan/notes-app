package tabitabi.picco.model;

import java.io.Serializable;
import java.util.Objects;

import org.joda.time.DateTime;

public class Note implements Serializable{
  
  
  private static final long serialVersionUID = -3686198567393508759L;
  
  private String text;
  private DateTime lastModification;
  
  @Override
  public boolean equals(Object obj){
    if(obj == null){
      return false;
    }
    
    if(obj == this) {
      return true;
    }
    
    if(!(obj instanceof Note)) {
      return false;
    }
    
    Note otherNote = (Note)obj;
    
    return (Objects.equals(otherNote.lastModification, this.lastModification) 
        && Objects.equals(otherNote.text, this.text));
  }
  
  public DateTime getLastModification() {
    return lastModification;
  }

  public String getText() {
    return text;
  }

  public int hashCode(){
    return Objects.hash(text,lastModification);
  }
  
  public void setLastModification(final DateTime lastModification) {
    this.lastModification = lastModification;
  }

  public void setText(final String text) {
    this.text = text;
  }
  
}
