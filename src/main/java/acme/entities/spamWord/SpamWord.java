package acme.entities.spamWord;

import javax.persistence.Entity;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SpamWord extends DomainEntity {
	
	protected static final long serialVersionUID = 1L;
	
	@NotBlank
	protected String word;
	
	@NotNull
	@DecimalMax("100.0") @DecimalMin("0.0")
	protected Double threshold;
	
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((this.word == null) ? 0 : this.word.hashCode());
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
		final SpamWord other = (SpamWord) obj;
		if (this.word == null) {
			if (other.word != null)
				return false;
		} else if (!this.word.equals(other.word))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SpamWord [word=" + this.word + "]";
	}
	
	
	

}
