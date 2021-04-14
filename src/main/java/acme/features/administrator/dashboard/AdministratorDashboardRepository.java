package acme.features.administrator.dashboard;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(t) from Task t where t.esPublico='True'")
	Integer numberOfPublicTask();
	
	@Query("select count(t) from Task t where t.esPublico='False'")
	Integer numberOfPrivateTask();
//	CURRENT_TIMESTAMP
	@Query("select count(t) from Task t where t.executionPeriod < CURRENT_TIMESTAMP")
	Integer numberOfFinishedTask();
	
	@Query("select count(t) from Task t where t.executionPeriod > CURRENT_TIMESTAMP")
	Integer numberOfNotFinishedTask();	
	
	@Query("select avg(t.workload) from Task t")
	Double averageNumberOfWorkload();

	@Query("select avg(t.executionPeriod) from Task t")
	Double averageNumberOfExecutionPeriods();
	
	@Query("select min(t.workload) from Task t")
	Integer minWorkload();

	@Query("select max(t.workload) from Task t")
	Integer maxWorkload();
	
	@Query("select min(t.executionPeriod) from Task t")
	LocalDateTime minExecutionPeriod();

	@Query("select max(t.executionPeriod) from Task t")
	LocalDateTime maxExecutionPeriod();
	
	@Query("select sqrt(sum((t.workload - (select avg(t.workload) from Task t))*(t.workload - (select avg(t.workload) from Task t)))) from Task t")
	Double deviationWorkload();
	
	@Query("select sqrt(sum((t.executionPeriod - (select avg(t.executionPeriod) from Task t))*(t.executionPeriod - (select avg(t.executionPeriod) from Task t)))) from Task t")
	Double deviationExecutionPeriod();
	
}

