<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.spam-word.form.label.word" path="word" readonly="true"/>
		
	<acme:form-submit code="anonymous.spam-word.form.button.create" action="/anonymous/spam-word/create"/>	
  	<acme:form-return code="anonymous.spam-word.form.button.return"/>
</acme:form>
