<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
    <acme:form-textbox code="anonymous.work-plan.list.label.title" path="title"/>
    <acme:form-textbox code="anonymous.work-plan.form.label.start" path="start"/>
    <acme:form-textbox code="anonymous.work-plan.form.label.end" path="end"/>
    <acme:form-textbox code="anonymous.work-plan.form.label.workload" path="workPlanWorkload"/>
    <acme:form-select code="anonymous.work-plan.form.label.isPublic" path="isPublic">
		<acme:form-option code="anonymous.work-plan.form.label.yes" value="true" selected="true"/>
		<acme:form-option code="anonymous.work-plan.form.label.no" value="false"/>
	</acme:form-select>
    <acme:form-return code="anonymous.work-plan.form.button.return"/>
</acme:form>