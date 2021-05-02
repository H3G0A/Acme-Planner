package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkplanDashboard implements Serializable {
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	Integer numberOfPublicWorkPlan;
	Integer numberOfPrivateWorkPlan;
	Integer numberOfFinishedWorkPlan;
	Integer numberOfNonFinishedWorkPlan;
	Double averageNumberOfWorkPlanPeriod;
	Double deviationOfWorkPlanPeriod;
	Double minWorkPlanPeriod;
	Double maxWorkPlanPeriod;
	Double averageNumberOfWorkPlanWorkload;
	Double deviationOfWorkPlanWorkload;
	Double minWorkPlanWorkload;
	Double maxWorkPlanWorkload;
}
