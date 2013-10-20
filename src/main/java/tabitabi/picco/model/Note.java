package tabitabi.picco.model;

import java.io.Serializable;
import java.util.Objects;

import org.joda.time.DateTime;

public class Note implements Serializable{

   private static final long serialVersionUID = -3686198567393508760L;

 private DateTime lastModification;
  private DateTime receiveDate;
   private String text;

  

   @Override
   public boolean equals(Object obj)
   {
      if (obj == null)
      {
         return false;
      }

      if (obj == this)
      {
         return true;
      }

      if (!(obj instanceof Note))
      {
         return false;
      }

      Note otherNote = (Note)obj;

      return (Objects.equals(otherNote.lastModification, this.lastModification) && Objects.equals(otherNote.text,
         this.text));
   }

   public DateTime getLastModification()
   {
      return lastModification;
   }
   
   public DateTime getReceiveDate()
   {
      return receiveDate;
   }
    

   public String getText()
   {
      return text+receiveDate;
   }

   public int hashCode()
   {
      return Objects.hash(text, lastModification);
   }

   public void setLastModification(final DateTime lastModification)
   {
      this.lastModification = lastModification;
   }
public void setReceiveDate(final DateTime receiveDate)
   {
      this.receiveDate = receiveDate;
   }
   public void setText(final String text)
   {
      this.text = text;
   }

}
