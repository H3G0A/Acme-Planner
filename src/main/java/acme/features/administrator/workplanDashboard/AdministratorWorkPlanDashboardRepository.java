package acme.features.administrator.workplanDashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorWorkPlanDashboardRepository extends AbstractRepository {

	@Query("select count(wp) from WorkPlan wp where wp.isPublic=true")
	Integer numberOfPublicWorkPlan();	
	
	@Query("select count(wp) from WorkPlan wp where wp.isPublic=false")
	Integer numberOfPrivateWorkPlan();
	
	@Query("select count(wp) from WorkPlan wp where wp.end < CURRENT_TIMESTAMP")
	Integer numberOfFinishedWorkPlan();
	
	@Query("select count(wp) from WorkPlan wp where wp.end > CURRENT_TIMESTAMP")
	Integer numberOfNonFinishedWorkPlan();
	
	@Query("select avg(wp.workPlanPeriod) from WorkPlan wp")
	Double averageNumberOfWorkPlanPeriod();
	
	@Query("select stddev(wp.workPlanPeriod) from WorkPlan wp")
	Double deviationOfWorkPlanPeriod();
	
	@Query("select min((wp.workPlanPeriod)) from WorkPlan wp")
	Double minWorkPlanPeriod();

	@Query("select max((wp.workPlanPeriod)) from WorkPlan wp")
	Double maxWorkPlanPeriod();
	
	@Query("select avg(wp.workPlanWorkload) from WorkPlan wp")
	Double averageNumberOfWorkPlanWorkload();
	
	@Query("select stddev(wp.workPlanWorkload) from WorkPlan wp")
	Double deviationOfWorkPlanWorkload();
	
	@Query("select min((wp.workPlanWorkload)) from WorkPlan wp")
	Double minWorkPlanWorkload();

	@Query("select max((wp.workPlanWorkload)) from WorkPlan wp")
	Double maxWorkPlanWorkload();
	
}
