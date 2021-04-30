<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly = "true">
	<acme:form-textbox code="anonymous.task.form.label.title" path="title"/>
	<acme:form-textbox code="anonymous.task.form.label.start" path="start"/>
	<acme:form-textbox code="anonymous.task.form.label.end" path="end"/>
	<acme:form-textbox code="anonymous.task.form.label.workload" path="workload"/>
	<acme:form-textarea code="anonymous.task.form.label.description" path="description"/>
	<acme:form-select code="anonymous.task.form.label.isPublic" path="isPublic">
		<acme:form-option code="anonymous.task.form.label.yes" value="true" selected="true"/>
		<acme:form-option code="anonymous.task.form.label.no" value="false"/>
	</acme:form-select>
	<acme:form-textbox code="anonymous.task.form.label.link" path="link"/>
	
	<acme:form-return code="anonymous.task.form.button.return"/>
</acme:form>