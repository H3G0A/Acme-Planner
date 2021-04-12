package acme.forms;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

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
	LocalDateTime				minExecutionPeriod;
	LocalDateTime				maxExecutionPeriod;
	Double						deviationWorkload;
	Double						deviationExecutionPeriod;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}