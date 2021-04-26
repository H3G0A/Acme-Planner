package acme.entities.tasks;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Manager;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Task extends DomainEntity{

	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(max=80)
	private String title;
	
	@NotNull
	private Date start;
	
	@NotNull
	private Date end;
	
	
	@Digits(integer = 10,fraction = 2)
	private Double workload;
	
	@NotBlank
	@Size(max=500)
	private String description;

	@NotNull
	private Boolean isPublic;
	
	@URL
	private String link;
	
	@ManyToOne
	private Manager manager;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
		result = prime * result + ((this.end == null) ? 0 : this.end.hashCode());
		result = prime * result + ((this.isPublic == null) ? 0 : this.isPublic.hashCode());
		result = prime * result + ((this.link == null) ? 0 : this.link.hashCode());
		result = prime * result + ((this.start == null) ? 0 : this.start.hashCode());
		result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
		result = prime * result + ((this.workload == null) ? 0 : this.workload.hashCode());
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
		final Task other = (Task) obj;
		if (this.description == null) {
			if (other.description != null)
				return false;
		} else if (!this.description.equals(other.description))
			return false;
		if (this.end == null) {
			if (other.end != null)
				return false;
		} else if (!this.end.equals(other.end))
			return false;
		if (this.isPublic == null) {
			if (other.isPublic != null)
				return false;
		} else if (!this.isPublic.equals(other.isPublic))
			return false;
		if (this.link == null) {
			if (other.link != null)
				return false;
		} else if (!this.link.equals(other.link))
			return false;
		if (this.start == null) {
			if (other.start != null)
				return false;
		} else if (!this.start.equals(other.start))
			return false;
		if (this.title == null) {
			if (other.title != null)
				return false;
		} else if (!this.title.equals(other.title))
			return false;
		if (this.workload == null) {
			if (other.workload != null)
				return false;
		} else if (!this.workload.equals(other.workload))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Task [title=" + this.title + ", start=" + this.start + ", end=" + this.end + ", workload=" + this.workload + ", description=" + this.description + ", isPublic=" + this.isPublic + ", link=" + this.link + "]";
	}

}