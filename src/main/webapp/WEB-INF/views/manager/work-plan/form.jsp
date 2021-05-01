<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<acme:form-hidden path="managerId"/>
	<acme:form-textbox code="manager.workPlan.form.label.title" path="title"/>
    <acme:form-textbox code="manager.workPlan.form.label.description" path="description"/>
    <acme:form-moment code="manager.workPlan.form.label.start" path="start"/>
    <acme:form-moment code="manager.workPlan.form.label.end" path="end"/>
    <acme:form-checkbox code="manager.workPlan.form.label.isPublic" path="isPublic"/>
    
    <acme:form-submit test="${command == 'show'}" code="manager.workPlan.form.button.update" action="/manager/work-plan/update"/>
    <acme:form-submit test="${command == 'show'}" code="manager.workPlan.form.button.delete" action="/manager/work-plan/delete"/>
    <acme:form-submit test="${command == 'create'}" code="manager.workPlan.form.button.create" action="/manager/work-plan/create"/>
	<acme:form-submit test="${command == 'update'}" code="manager.workPlan.form.button.update" action="/manager/work-plan/update"/>
    <acme:form-submit test="${command == 'delete'}" code="manager.workPlan.form.button.delete" action="/manager/work-plan/delete"/>   
    <acme:form-return code="manager.task.form.button.return"/>

</acme:form>