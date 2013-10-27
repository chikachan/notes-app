package tabitabi.picco.model;

import java.io.Serializable;
import java.util.Objects;

import org.joda.time.DateTime;

public class Note implements Serializable
{

   private static final long serialVersionUID = -3686198567393508761L;

   private DateTime lastModification;

   private DateTime receivingDate;

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

      return Objects.equals(otherNote.lastModification, this.lastModification) 
          && Objects.equals(otherNote.text, this.text) 
          && Objects.equals(otherNote.receivingDate, this.receivingDate);
   }

   public DateTime getLastModification()
   {
      return lastModification;
   }

   public DateTime getReceivingDate()
   {
      return receivingDate;
   }

   public String getText()
   {
      return text + receivingDate;
   }

   public int hashCode()
   {
      return Objects.hash(text, lastModification, receivingDate);
   }

   public void setLastModification(final DateTime lastModification)
   {
      this.lastModification = lastModification;
   }

   public void setReceivingDate(final DateTime receiveDate)
   {
      this.receivingDate = receiveDate;
   }

   public void setText(final String text)
   {
      this.text = text;
   }

}
