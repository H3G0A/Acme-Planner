package acme.entities.shouts;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Shout extends DomainEntity {
	
	protected static final long serialVersionUID = 1L;

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	protected Date moment;
	
	@NotBlank
	@Size(min = 5, max = 25)
	protected String author;
	
	@NotEmpty
	@Size(max = 100)
	protected String text;
	
	@URL
	protected String info;

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((this.author == null) ? 0 : this.author.hashCode());
//		result = prime * result + ((this.link == null) ? 0 : this.link.hashCode());
//		result = prime * result + ((this.moment == null) ? 0 : this.moment.hashCode());
//		result = prime * result + ((this.text == null) ? 0 : this.text.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(final Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (this.getClass() != obj.getClass())
//			return false;
//		final Shout other = (Shout) obj;
//		if (this.author == null) {
//			if (other.author != null)
//				return false;
//		} else if (!this.author.equals(other.author))
//			return false;
//		if (this.link == null) {
//			if (other.link != null)
//				return false;
//		} else if (!this.link.equals(other.link))
//			return false;
//		if (this.moment == null) {
//			if (other.moment != null)
//				return false;
//		} else if (!this.moment.equals(other.moment))
//			return false;
//		if (this.text == null) {
//			if (other.text != null)
//				return false;
//		} else if (!this.text.equals(other.text))
//			return false;
//		return true;
//	}
//
//	@Override
//	public String toString() {
//		return "Shout [moment=" + this.moment + ", author=" + this.author + ", text=" + this.text + ", link=" + this.link + "]";
//	}
	
	

	
	}
