<%@page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
    <acme:form-double path="thresholdNumber" code="administrator.threshold.form.label.threshold" />
    <jstl:if test="${!readonly}">
		<acme:form-submit action="/administrator/threshold/update" code="administrator.threshold.form.button.update" />
		<acme:form-submit action="/administrator/threshold/reset" code="administrator.threshold.form.button.reset" />
	</jstl:if>
</acme:form>