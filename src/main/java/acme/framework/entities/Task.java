package acme.framework.entities;

import java.time.LocalDateTime;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

public class Task {

	@NotBlank
	@Max(80)
	private String title;
	
	@Future
	private LocalDateTime executionPeriod;
	
	@Digits(integer = 10,fraction = 2)
	private Integer workload;
	
	@NotBlank
	@Max(500)
	private String description;

	
	public String getTitle() {
		return this.title;
	}

	
	public void setTitle(final String title) {
		this.title = title;
	}

	
	public LocalDateTime getExecutionPeriod() {
		return this.executionPeriod;
	}

	
	public void setExecutionPeriod(final LocalDateTime executionPeriod) {
		this.executionPeriod = executionPeriod;
	}

	
	public Integer getWorkload() {
		return this.workload;
	}

	
	public void setWorkload(final Integer workload) {
		this.workload = workload;
	}

	
	public String getDescription() {
		return this.description;
	}

	
	public void setDescription(final String description) {
		this.description = description;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
		result = prime * result + ((this.executionPeriod == null) ? 0 : this.executionPeriod.hashCode());
		result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
		result = prime * result + ((this.workload == null) ? 0 : this.workload.hashCode());
		return result;
	}


	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		final Task other = (Task) obj;
		if (this.description == null) {
			if (other.description != null)
				return false;
		} else if (!this.description.equals(other.description))
			return false;
		if (this.executionPeriod == null) {
			if (other.executionPeriod != null)
				return false;
		} else if (!this.executionPeriod.equals(other.executionPeriod))
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
		return "Task [title=" + this.title + ", executionPeriod=" + this.executionPeriod + ", workload=" + this.workload + ", description=" + this.description + "]";
	}
	
	
}
