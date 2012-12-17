package tabitabi.picco.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Getter 
@Setter
public class Account implements Serializable {

	private static final long serialVersionUID = 3686198567393508762L;

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

	
	public Account(final Account other){
		email =other.email;
		id = other.id;
		notesIds = other.notesIds;
	}

	@Override
	public String toString(){
		return email;
	}

}
