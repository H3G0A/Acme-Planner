<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<h2>
	<acme:message code="administrator.workplan.dashboard.form.title"/>
</h2>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.workplan.dashboard.form.title"/>
	</caption>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.numberOfPublicWorkPlan"/>
		</th>
		<td>
			<acme:print value="${numberOfPublicWorkPlan}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.numberOfPrivateWorkPlan"/>
		</th>
		<td>
			<acme:print value="${numberOfPrivateWorkPlan}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.numberOfFinishedWorkPlan"/>
		</th>
		<td>
			<acme:print value="${numberOfFinishedWorkPlan}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.numberOfNonFinishedWorkPlan"/>
		</th>
		<td>
			<acme:print value="${numberOfNonFinishedWorkPlan}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.averageNumberOfWorkPlanPeriod"/>
		</th>
		<td>
			<acme:print value="${averageNumberOfWorkPlanPeriod}"/>
		</td>
	</tr>
	<tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.deviationOfWorkPlanPeriod"/>
		</th>
		<td>
			<acme:print value="${deviationOfWorkPlanPeriod}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.minWorkPlanPeriod"/>
		</th>
		<td>
			<acme:print value="${minWorkPlanPeriod}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.maxWorkPlanPeriod"/>
		</th>
		<td>
			<acme:print value="${maxWorkPlanPeriod}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.averageNumberOfWorkPlanWorkload"/>
		</th>
		<td>
			<acme:print value="${averageNumberOfWorkPlanWorkload}"/>
		</td>
	</tr>
	<tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.deviationOfWorkPlanWorkload"/>
		</th>
		<td>
			<acme:print value="${deviationOfWorkPlanWorkload}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.minWorkPlanWorkload"/>
		</th>
		<td>
			<acme:print value="${minWorkPlanWorkload}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.maxWorkPlanWorkload"/>
		</th>
		<td>
			<acme:print value="${maxWorkPlanWorkload}"/>
		</td>
	</tr>
</table>

<h2>
	<acme:message
		code="administrator.workplan.dashboard.form.title.application-statuses" />
</h2>
<div>
	<canvas id="canvas"></canvas>
</div>
<script type="text/javascript">
	$(document).ready(
			function() {
				var data = {
					labels : [
							"TOTAL", "NOT PUBLISHED", "PUBLISHED"
					],
					datasets : [
						{
							data : [
									<jstl:out value="${numberOfWorkPlan}" />,
									<jstl:out value="${numberOfPrivateWorkPlan}" />,
									<jstl:out value="${numberOfPublicWorkPlan}" />
							],
							backgroundColor : [
									'rgba(124, 252, 0, 1)', 'rgba(45, 144, 221, 1)', 'rgba(205, 205, 3, 1)'
							]
						}
					]
				};
				
				var total = <jstl:out value="${numberOfWorkPlan}" />;
				var options = {
					legend : {
						display : false
					},
					scales : {
		                yAxes : [
		                    {
		                        ticks : {
		                            suggestedMin : 0.0,
		                            suggestedMax : 1.0
		                        }
		                    }
		                ]
		            },
					title : {
						display : true,
						text : "WorkPlans: " + total
					}
				};
				var canvas, context;
				canvas = document.getElementById("canvas");
				context = canvas.getContext("2d");
				new Chart(context, {
					type : "bar",
					data : data,
					options : options
				});
			});
</script>
