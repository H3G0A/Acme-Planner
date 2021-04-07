
<%--
- list.jsp
-
- Copyright (C) 2012-2021 Rafael Corchuelo.
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

<acme:list>
	<acme:list-column code="administrator.task.list.label.title" path="title" width="15%"/>
	<acme:list-column code="administrator.task.list.label.executionPeriod" path="executionPeriod" width="15%"/>
	<acme:list-column code="administrator.task.list.label.workload" path="workload" width="15%"/>		
	<acme:list-column code="administrator.task.list.label.description" path="description" width="15%"/>		
	<acme:list-column code="administrator.task.list.label.link" path="link" width="15%"/>		
	<acme:list-column code="administrator.task.list.label.esPublico" path="esPublico" width="15%"/>		
</acme:list>