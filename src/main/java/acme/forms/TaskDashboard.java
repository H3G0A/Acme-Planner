package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer						numberOfPublicTask;
	Integer						numberOfPrivateTask;
	Integer						numberOfFinishedTask;
	Integer						numberOfNotFinishedTask;
	Double						averageNumberOfWorkload;
	Double						averageNumberOfExecutionPeriods;
	Integer						minWorkload;
	Integer						maxWorkload;
	Double						minExecutionPeriod;
	Double						maxExecutionPeriod;
	Double						deviationWorkload;
	Double						deviationExecutionPeriod;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}