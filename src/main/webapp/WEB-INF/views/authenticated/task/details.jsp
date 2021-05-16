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



<acme:form>

	<acme:form-hidden path="authenticatedId"/>
    <acme:form-textbox code="authenticated.task.details.label.title" path="title"/>
    <acme:form-textbox code="authenticated.task.details.label.description" path="description"/>
    <acme:form-url code="authenticated.task.details.label.link" path="link"/>
    <acme:form-moment code="authenticated.task.details.label.start" path="start"/>
    <acme:form-moment code="authenticated.task.details.label.end" path="end"/>
    <acme:form-double code="authenticated.task.details.label.workload" path="workload"/>
    <acme:form-checkbox code="authenticated.task.details.label.isPublic" path="isPublic"/>
 
    <acme:form-return code="authenticated.task.details.button.return"/>
</acme:form>
