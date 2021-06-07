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
import javax.validation.constraints.Size;

import acme.entities.roles.Management;
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
	@Size(max=80)
	protected String title;
	
	@NotBlank
	@Size(max=500)
	protected String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date start;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date end;
	
	protected Boolean isPublic;

	protected Double workPlanWorkload;
	
	protected Double workPlanPeriod;

	@ManyToMany(fetch = FetchType.EAGER)
	protected Collection<@Valid Task> tasks;

	@ManyToOne
	protected Management management;
	
	
	@Transient
	public Boolean isFinished() {
		Date now;
		now = new Date();
		return now.after(this.end);
	}
    
  public void setWorkPlanPeriod() {
      this.workPlanPeriod = (double) (this.end.getTime() - this.start.getTime()) / (1000 * 3600);
  }

  public void setWorkPlanWorkload() {
      this.workPlanWorkload = this.tasks.stream().mapToDouble(Task::getWorkload).sum();
  }

  public Double getWorkPlanWorkload() {
		return this.tasks.stream().mapToDouble(Task::getWorkload).sum();
	}
  
}
