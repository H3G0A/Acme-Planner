<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="false">
	<acme:form-textbox code="administrator.spam-word.form.label.word" path="word"/>
	<acme:form-textbox code="administrator.spam-word.form.label.threshold" path="threshold"/>
		
	<jstl:if test="${command=='create'}">
    	<acme:form-submit code="administrator.spam-word.form.button.create" action="/administrator/spam-word/create"/>
    </jstl:if>
    <acme:form-submit test="${command == 'show'}" code="administrator.spam-word.form.button.update" action="/administrator/spam-word/update"/>
  	<acme:form-submit test="${command == 'show'}" code="administrator.spam-word.form.button.delete" action="/administrator/spam-word/delete"/>
  	
  	<acme:form-return code="administrator.spam-word.form.button.return"/>
</acme:form>
