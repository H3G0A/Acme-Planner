<%--
- form.jsp
-
- Copyright (c) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2>
	<acme:message code="administrator.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.dashboard.form.title.general-indicators"/>
	</caption>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.numberOfPublicTask"/>
		</th>
		<td>
			<acme:print value="${numberOfPublicTask}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.numberOfPrivateTask"/>
		</th>
		<td>
			<acme:print value="${numberOfPrivateTask}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.numberOfFinishedTask"/>
		</th>
		<td>
			<acme:print value="${numberOfFinishedTask}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.numberOfNotFinishedTask"/>
		</th>
		<td>
			<acme:print value="${numberOfNotFinishedTask}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.averageNumberOfWorkload"/>
		</th>
		<td>
			<acme:print value="${averageNumberOfWorkload}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.averageNumberOfExecutionPeriods"/>
		</th>
		<td>
			<acme:print value="${averageNumberOfExecutionPeriods}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minWorkload"/>
		</th>
		<td>
			<acme:print value="${minWorkload}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maxWorkload"/>
		</th>
		<td>
			<acme:print value="${maxWorkload}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minExecutionPeriod"/>
		</th>
		<td>
			<acme:print value="${minExecutionPeriod}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maxExecutionPeriod"/>
		</th>
		<td>
			<acme:print value="${maxExecutionPeriod}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviationWorkload"/>
		</th>
		<td>
			<acme:print value="${deviationWorkload}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviationExecutionPeriod"/>
		</th>
		<td>
			<acme:print value="${deviationExecutionPeriod}"/>
		</td>
	</tr>
</table>

<h2>
	<acme:message code="administrator.dashboard.form.title.application-statuses"/>
</h2>

<div>
	<canvas id="canvas"></canvas>
</div>