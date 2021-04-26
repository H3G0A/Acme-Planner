package acme.entities.workPlan;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.entities.tasks.Task;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WorkPlan extends DomainEntity {
	
	protected static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date start;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date end;
	
	protected Boolean isPublic;

	
	@ManyToMany(fetch = FetchType.EAGER)
	protected Collection<@Valid Task> tasks;
	
	public Double getWorkload() {
		return this.tasks.stream().mapToDouble(x->x.getWorkload()).sum();
	}

}