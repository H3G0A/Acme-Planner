<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:list>
	<acme:list-column code="administrator.spam-word.list.label.word" path="word"/>
	<acme:list-column code="administrator.spam-word.list.label.threshold" path="threshold"/>
	
</acme:list>
