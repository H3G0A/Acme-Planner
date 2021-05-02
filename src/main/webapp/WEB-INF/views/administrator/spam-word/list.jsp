<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<<<<<<< Updated upstream
<acme:list readonly="true">
	<acme:list-column code="administrator.spam-word.list.label.word" path="word" width="100%"/>


=======
<acme:list>
	<acme:list-column code="administrator.spam-word.list.label.word" path="word"/>
	<acme:list-column code="administrator.spam-word.list.label.threshold" path="threshold"/>
	
>>>>>>> Stashed changes
</acme:list>
