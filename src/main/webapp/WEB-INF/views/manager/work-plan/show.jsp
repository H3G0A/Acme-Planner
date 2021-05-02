<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<h2>
	<acme:message code="administrator.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<caption>
		<acme:print value="${title}"/>
	</caption>
	<tr>
		<th scope="row">
			<acme:message code="manager.workPlan.form.label.start"/>
		</th>
		<td>
			<acme:print value="${start}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="manager.workPlan.form.label.end"/>
		</th>
		<td>
			<acme:print value="${end}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="manager.workPlan.form.label.description"/>
		</th>
		<td>
			<acme:print value="${description}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="manager.workPlan.form.label.workload"/>
		</th>
		<td>
			<acme:print value="${workload}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="manager.workPlan.form.label.executionPeriod"/>
		</th>
		<td>
			<acme:print value="${executionPeriod}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="manager.workPlan.form.label.tasks"/>
		</th>
		<td>
			<acme:print value="${tasks}"/>
		</td>
	</tr>
</table>

<div>
	<canvas id="canvas"></canvas>
</div>