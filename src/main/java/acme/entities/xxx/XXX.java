package acme.entities.xxx;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class XXX extends DomainEntity {
	
	protected static final long serialVersionUID = 1L;

	@NotNull
//	@UniqueElements
	@Column(unique = true)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date dating;
	
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	protected Date moment;
	
	@NotNull
	@Positive
	protected Double amount;
	
	@NotNull
	protected Boolean flag;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((this.amount == null) ? 0 : this.amount.hashCode());
		result = prime * result + ((this.dating == null) ? 0 : this.dating.hashCode());
		result = prime * result + ((this.flag == null) ? 0 : this.flag.hashCode());
		result = prime * result + ((this.moment == null) ? 0 : this.moment.hashCode());
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
		final XXX other = (XXX) obj;
		if (this.amount == null) {
			if (other.amount != null)
				return false;
		} else if (!this.amount.equals(other.amount))
			return false;
		if (this.dating == null) {
			if (other.dating != null)
				return false;
		} else if (!this.dating.equals(other.dating))
			return false;
		if (this.flag == null) {
			if (other.flag != null)
				return false;
		} else if (!this.flag.equals(other.flag))
			return false;
		if (this.moment == null) {
			if (other.moment != null)
				return false;
		} else if (!this.moment.equals(other.moment))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "XXX [dating=" + this.dating + ", moment=" + this.moment + ", amount=" + this.amount + ", flag=" + this.flag + "]";
	}
	
	

}
