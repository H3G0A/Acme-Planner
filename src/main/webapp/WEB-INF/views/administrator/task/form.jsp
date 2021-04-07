
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

<acme:form>
	<acme:form-textbox code="administrator.task.form.label.title" path="title"/>
	<acme:form-textbox code="administrator.task.form.label.workload" path="workload"/>		
	<acme:form-textarea code="administrator.task.form.label.description" path="description"/>		
	<acme:form-textbox code="administrator.task.form.label.link" path="link"/>		
	<acme:form-checkbox code="administrator.task.form.label.esPublico" path="esPublico"/>		
	
	<acme:form-submit code="administrator.task.form.button.create" action="/administrator/task/create"/>
	<acme:form-return code="administrator.task.form.button.return"/>
	
</acme:form>