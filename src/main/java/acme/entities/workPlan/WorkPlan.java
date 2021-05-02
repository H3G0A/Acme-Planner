package acme.entities.workPlan;

import java.beans.Transient;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WorkPlan extends DomainEntity {
	
	protected static final long serialVersionUID = 1L;
	
	@NotBlank
	protected String title;
	
	@NotBlank
	protected String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date start;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date end;
	
	protected Boolean isPublic;

	
	@ManyToMany(fetch = FetchType.EAGER)
	protected Collection<@Valid Task> tasks;
	
	@ManyToOne
	protected Manager manager;
	
	protected double workload;
	
	protected double executionPeriod;
	
	public Double getWorkload() {
		return this.tasks.stream().mapToDouble(x->x.getWorkload()).sum();
	}
	
	public void setExecutionPeriod() {
		this.executionPeriod = (double) (this.end.getTime() - this.start.getTime()) / (1000 * 3600);
	}
	
	@Transient
	public Boolean isFinished() {
		Date now;
		now = new Date();
		return now.after(this.end);
	}

	public void setWorkload() {
		this.workload = this.tasks.stream().mapToDouble(Task::getWorkload).sum();
	}

}
