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
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${averageNumberOfWorkPlanPeriod}"/>
			<acme:message code="administrator.workplan.dashboard.form.label.hours"/>
		</td>
	</tr>
	<tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.deviationOfWorkPlanPeriod"/>
		</th>
		<td>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${deviationOfWorkPlanPeriod}"/>
			<acme:message code="administrator.workplan.dashboard.form.label.hours"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.minWorkPlanPeriod"/>
		</th>
		<td>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${minWorkPlanPeriod}"/>
			<acme:message code="administrator.workplan.dashboard.form.label.hours"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.maxWorkPlanPeriod"/>
		</th>
		<td>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${maxWorkPlanPeriod}"/>
			<acme:message code="administrator.workplan.dashboard.form.label.hours"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.averageNumberOfWorkPlanWorkload"/>
		</th>
		<td>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${averageNumberOfWorkPlanWorkload}"/>
			<acme:message code="administrator.workplan.dashboard.form.label.hours"/>
		</td>
	</tr>
	<tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.deviationOfWorkPlanWorkload"/>
		</th>
		<td>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${deviationOfWorkPlanWorkload}"/>
			<acme:message code="administrator.workplan.dashboard.form.label.hours"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.minWorkPlanWorkload"/>
		</th>
		<td>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${minWorkPlanWorkload}"/>
			<acme:message code="administrator.workplan.dashboard.form.label.hours"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.maxWorkPlanWorkload"/>
		</th>
		<td>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${maxWorkPlanWorkload}"/>
			<acme:message code="administrator.workplan.dashboard.form.label.hours"/>
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
							"PRIVATE", "PUBLIC"
					],
					datasets : [
						{
							data : [
									<jstl:out value="${numberOfPrivateWorkPlan}" />,
									<jstl:out value="${numberOfPublicWorkPlan}" />
							],
							backgroundColor : [
									'rgba(45, 144, 221, 1)', 'rgba(205, 205, 3, 1)'
							]
						}
					]
				};
				
				var total = <jstl:out value="${numberOfPublicWorkPlan} + ${numberOfPrivateWorkPlan}" />;
				var options = {
					legend : {
						display : true
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
					type : "pie",
					data : data,
					options : options
				});
			});
</script>