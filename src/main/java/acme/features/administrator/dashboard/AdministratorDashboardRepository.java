package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(t) from Task t where t.isPublic='True'")
	Integer numberOfPublicTask();
	
	@Query("select count(t) from Task t where t.isPublic='False'")
	Integer numberOfPrivateTask();
//	CURRENT_TIMESTAMP
	@Query("select count(t) from Task t where t.end < CURRENT_TIMESTAMP")
	Integer numberOfFinishedTask();
	
	@Query("select count(t) from Task t where t.end > CURRENT_TIMESTAMP")
	Integer numberOfNotFinishedTask();	
	
	@Query("select avg(t.workload) from Task t")
	Double averageNumberOfWorkload();

	@Query("select avg((t.end)-(t.start)) from Task t")
	Double averageNumberOfExecutionPeriods();
	
	@Query("select min(t.workload) from Task t")
	Integer minWorkload();

	@Query("select max(t.workload) from Task t")
	Integer maxWorkload();
	
	@Query("select min((t.end)-(t.start)) from Task t")
	Double minExecutionPeriod();

	@Query("select max((t.end)-(t.start)) from Task t")
	Double maxExecutionPeriod();
	
	@Query("select sqrt(sum((t.workload - (select avg(t.workload) from Task t))*(t.workload - (select avg(t.workload) from Task t)))) from Task t")
	Double deviationWorkload();
	
	@Query("select sqrt(sum(((t.end)-(t.start) - (select avg((t.end)-(t.start)) from Task t))*((t.end)-(t.start) - (select avg((t.end)-(t.start)) from Task t)))) from Task t")
	Double deviationExecutionPeriod();
	
}

