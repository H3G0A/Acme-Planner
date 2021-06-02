package acme.entities.shouts;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import acme.entities.xxx.XXX;
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
	@NotBlank
	@Size(max = 100)
	protected String text;
	
	@URL
	protected String info;
	
	@NotNull
	@OneToOne
	private XXX xxx;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((this.author == null) ? 0 : this.author.hashCode());
		result = prime * result + ((this.info == null) ? 0 : this.info.hashCode());
		result = prime * result + ((this.moment == null) ? 0 : this.moment.hashCode());
		result = prime * result + ((this.text == null) ? 0 : this.text.hashCode());
		result = prime * result + ((this.xxx == null) ? 0 : this.xxx.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		final Shout other = (Shout) obj;
		if (this.author == null) {
			if (other.author != null)
				return false;
		} else if (!this.author.equals(other.author))
			return false;
		if (this.info == null) {
			if (other.info != null)
				return false;
		} else if (!this.info.equals(other.info))
			return false;
		if (this.moment == null) {
			if (other.moment != null)
				return false;
		} else if (!this.moment.equals(other.moment))
			return false;
		if (this.text == null) {
			if (other.text != null)
				return false;
		} else if (!this.text.equals(other.text))
			return false;
		if (this.xxx == null) {
			if (other.xxx != null)
				return false;
		} else if (!this.xxx.equals(other.xxx))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Shout [moment=" + this.moment + ", author=" + this.author + ", text=" + this.text + ", info=" + this.info + ", xxx=" + this.xxx + "]";
	}

	
	}
