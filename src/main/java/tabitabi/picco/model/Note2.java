package tabitabi.picco.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Note2 implements Serializable
{

   private static final long serialVersionUID = 3686198567393508768L;


   private long accountId;
   @Id
   @GeneratedValue
   private long id;
   private Date lastModification;
   private Date receivingDate;
   @Column(length=1024)
   private String text;

   public Note2(){}
   
	public Note2(final Note2 other) {
		accountId = other.accountId;
		id = other.id;
		lastModification = other.lastModification;
		receivingDate = other.receivingDate;
		text = other.text;
	}
   
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

	public long getAccountId() {
		return accountId;
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

   public void setAccountId(long accountId){
	   this.accountId = accountId;
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
