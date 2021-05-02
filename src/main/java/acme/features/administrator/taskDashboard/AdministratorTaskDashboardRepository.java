package acme.features.administrator.taskDashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorTaskDashboardRepository extends AbstractRepository {

	@Query("select count(t) from Task t where t.isPublic=true")
	Integer numberOfPublicTask();
	
	@Query("select count(t) from Task t where t.isPublic=false")
	Integer numberOfPrivateTask();
//	CURRENT_TIMESTAMP
	@Query("select count(t) from Task t where t.end < CURRENT_TIMESTAMP")
	Integer numberOfFinishedTask();
	
	@Query("select count(t) from Task t where t.end > CURRENT_TIMESTAMP")
	Integer numberOfNotFinishedTask();	
	
	@Query("select avg(t.workload) from Task t")
	Double averageNumberOfWorkload();

	@Query("select avg((t.executionPeriod)) from Task t")
	Double averageNumberOfExecutionPeriods();
	
	@Query("select min(t.workload) from Task t")
	Integer minWorkload();

	@Query("select max(t.workload) from Task t")
	Integer maxWorkload();
	
	@Query("select min((t.executionPeriod)) from Task t")
	Double minExecutionPeriod();

	@Query("select max((t.executionPeriod)) from Task t")
	Double maxExecutionPeriod();
	
	@Query("select stddev(t.workload) from Task t")
	Double deviationWorkload();
	
	@Query("select stddev(t.executionPeriod) from Task t")
	Double deviationExecutionPeriod();
	
}

