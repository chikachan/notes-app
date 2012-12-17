package tabitabi.picco.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
public @Data class Note2 implements Serializable {

	private static final long serialVersionUID = 3686198567393508769L;

	private long accountId;
	@Id
	@GeneratedValue
	private long id;
	private Date lastModification;
	private Date receivingDate;
	@Column(length = 1024)
	private String text;

	public Note2() {
	}

	public Note2(final Note2 other) {
		accountId = other.accountId;
		id = other.id;
		lastModification = other.lastModification;
		receivingDate = other.receivingDate;
		text = other.text;
	}

}
