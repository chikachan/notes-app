package tabitabi.picco.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Account implements Serializable {

	private static final long serialVersionUID = 3686198567393508761L;

	@Column(unique=true)
	private String email;
	@Id
	@GeneratedValue
	private long id;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "NOTE", 
		joinColumns = @JoinColumn(name = "ACCOUNTID"))
	@Column(name = "ID")
	private Set<Long> notesIds;

	public Account() {
	}
	
	public Account(final Account other){
		email =other.email;
		id = other.id;
		notesIds = other.notesIds;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		if (!(obj instanceof Account)) {
			return false;
		}

		Account otherAccount = (Account) obj;

		return Objects.equals(email, otherAccount.email) && 
				id == otherAccount.id &&
				Objects.equals(notesIds, otherAccount.notesIds);
	}

	public String getEmail() {
		return email;
	}

	public long getId() {
		return id;
	}
	
	public Set<Long> getNotesIds(){
		return notesIds;
	}

	@Override
	public int hashCode(){
		return Objects.hash(email,id,notesIds);
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void setNotesIds(Set<Long> notesIds){
		this.notesIds = notesIds;
	}
	
	@Override
	public String toString(){
		return email;
	}

}
