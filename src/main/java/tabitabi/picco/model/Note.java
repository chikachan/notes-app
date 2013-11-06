package tabitabi.picco.model;

import static javax.persistence.TemporalType.DATE;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
public class Note implements Serializable
{

   private static final long serialVersionUID = 3686198567393508763L;

   @Id
   @GeneratedValue
   private long id;
   @Temporal(DATE)
   private Date lastModification;
   @Temporal(DATE)
   private Date receivingDate;
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

      return (otherNote.id == this.id)
    	  &&  Objects.equals(otherNote.lastModification, this.lastModification) 
          && Objects.equals(otherNote.text, this.text) 
          && Objects.equals(otherNote.receivingDate, this.receivingDate);
   }

   public Date getLastModification()
   {
      return lastModification;
   }

   public Date getReceivingDate()
   {
      return receivingDate;
   }

   public String getText()
   {
      return text + receivingDate;
   }

   public int hashCode()
   {
      return Objects.hash(id, text, lastModification, receivingDate);
   }

   public void setLastModification(final Date lastModification)
   {
      this.lastModification = lastModification;
   }

   public void setReceivingDate(final Date receiveDate)
   {
      this.receivingDate = receiveDate;
   }

   public void setText(final String text)
   {
      this.text = text;
   }

}
