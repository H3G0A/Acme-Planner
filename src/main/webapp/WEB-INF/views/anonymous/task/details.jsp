<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<table class="table table-sm">
	<caption>
		<acme:message code="anonymous.task.details.label.title.general-indicators"/>
	</caption>	
	<tr>
		<th scope="row">
			<acme:message code="anonymous.task.details.label.title"/>
		</th>
		<td>
			<acme:print value="${title}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="anonymous.task.details.label.start"/>
		</th>
		<td>
			<acme:print value="${start}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="anonymous.task.details.label.end"/>
		</th>
		<td>
			<acme:print value="${end}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="anonymous.task.details.label.workload"/>
		</th>
		<td>
			<acme:print value="${workload}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="anonymous.task.details.label.description"/>
		</th>
		<td>
			<acme:print value="${description}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="anonymous.task.details.label.isPublic"/>
		</th>
		<td>
			<acme:print value="${isPublic}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="anonymous.task.details.label.link"/>
		</th>
		<td>
			<acme:print value="${link}"/>
		</td>
	</tr>
	
</table>