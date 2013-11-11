package tabitabi.picco.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="NOTES")
public class Note2 implements Serializable
{

   private static final long serialVersionUID = 3686198567393508767L;

 
   @Id
   private long id;
   private Date lastModification;
   private Date receivingDate;
   private String text;

   public Note2(){}
   
   
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

      if (!(obj instanceof Note2))
      {
         return false;
      }

      Note2 otherNote = (Note2)obj;

      return (otherNote.id == this.id)
    	  &&  Objects.equals(otherNote.lastModification, this.lastModification) 
          && Objects.equals(otherNote.text, this.text) 
          && Objects.equals(otherNote.receivingDate, this.receivingDate);
   }

   public long getId(){
	return id;   
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
      return text;
   }

   public int hashCode()
   {
      return Objects.hash(id, text, lastModification, receivingDate);
   }

   public void setId(long id){
	   this.id = id;
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
