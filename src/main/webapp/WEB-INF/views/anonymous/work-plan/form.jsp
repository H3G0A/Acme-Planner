<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
    <acme:form-textbox code="anonymous.work-plan.form.label.id" path="id"/>
    <acme:form-textbox code="anonymous.work-plan.form.label.isPublic" path="isPublic"/>
    <acme:form-textbox code="anonymous.work-plan.form.label.start" path="start"/>
    <acme:form-textbox code="anonymous.work-plan.form.label.end" path="end"/>
    <acme:form-textbox code="anonymous.work-plan.form.label.workload" path="workload"/>
    <acme:form-return code="anonymous.work-plan.form.button.return"/>
</acme:form>