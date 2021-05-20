<%@page language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	

<acme:form>
    <acme:form-hidden path="id"/>
    <acme:form-textbox code="manager.workPlan.form.label.title" path="title"/>
    <jstl:if test="${command!='create'}">    
   		<acme:form-textbox  readonly="true" code="manager.workPlan.form.label.workload" path="workload"/> 	
   		<acme:form-textbox  readonly="true" code="manager.workPlan.form.label.executionPeriod" path="executionPeriod"/> 	   		
    </jstl:if>
    <acme:form-textbox code="manager.workPlan.form.label.description" path="description"/>
    <acme:form-moment code="manager.workPlan.form.label.start" path="start"/>
    <acme:form-moment code="manager.workPlan.form.label.end" path="end"/>   
    <jstl:if test="${command=='create'}">    
    	<acme:form-checkbox code="manager.workPlan.form.label.isPublic" path="isPublic"/>
    </jstl:if>
    <jstl:if test="${command=='create'}">  		   		   	   		    		
	    <acme:form-submit code="manager.workPlan.form.button.create" action="/management/work-plan/create"/>    
    </jstl:if>  
    
    <acme:form-submit test="${canPublish && (command=='show'|| command =='update')}" code="manager.workplan.form.button.publish" action="/management/work-plan/publish"/>
    <acme:form-submit test="${(command =='show')}" code="manager.workPlan.form.button.update" action="/management/work-plan/update"/>
    <acme:form-submit test="${(command=='show'|| command =='delete')}" code="manager.workPlan.form.button.delete" action="/management/work-plan/delete"/>
    <acme:form-return code="manager.workPlan.form.button.return"/>
</acme:form>
<br><br>

<!-- TASK TABLE -->
<jstl:if test="${command=='show'|| command =='update'}">    
		   <div class="table-responsive">
		   <table class="table table-striped table-condensed table-hover nowrap w-100">
		   	  <caption><acme:message code="manager.workPlan.form.label.tasks"/></caption>
			  <thead>
				    <tr>
				      <th scope="col"><acme:message code="manager.workPlan.form.label.tasks.id"/></th>
				      <th scope="col"><acme:message code="manager.workPlan.form.label.tasks.title"/></th>
				      <th scope="col"><acme:message code="manager.workPlan.form.label.tasks.public"/></th>
				      <th scope="col"></th>
				    </tr>
				  </thead>
				  <tbody>
				  	<c:forEach items="${tasks}" var="task">
					  	<tr class="table-light">
					      <td>${task.id}</td>
					      <td>${task.title}</td>
					      <td><acme:message code="manager.workPlan.form.label.tasks.public.${task.isPublic}"/></td>
					      <td>
					      	<acme:form>
					      		<input type="hidden" name="taskId" value="${task.id}">
					      		<input type="hidden" name="workplanId" value="${id}">
					      		<acme:form-submit code="manager.workPlan.form.button.removeTask" action="/management/work-plan/remove_task"/>   
					      	</acme:form>
					      </td>
					    </tr>
					</c:forEach>
				    </tbody>
			   </table>
		    </div>
    </jstl:if>

<!-- ADD TASK  -->
<jstl:if test="${(command=='show'|| command =='update')}">    
	<center>
	<acme:form>
		<acme:form-select code="manager.workPlan.form.select.addTask" path="taskSelected">
			<c:forEach items="${tasksEneabled}" var="task">
				<acme:form-option code="${task.title} - ${task.description}" value="${task.id}"/>	
			</c:forEach>
		</acme:form-select>
	<acme:form-submit code="manager.workPlan.form.button.addTask" action="/management/work-plan/add_task"/>    
	</acme:form>
	</center>
</jstl:if>

<!-- SUGGESTION -->
<jstl:if test="${(command=='show' && not empty tasks)}">
	<br>   
	<b><acme:message code="manager.workPlan.form.label.suggestion.start"/><jstl:out value="${suggestedStart}"></jstl:out><br></b>
	<b><acme:message code="manager.workPlan.form.label.suggestion.end"/><jstl:out value="${suggestedEnd}"></jstl:out><br></b>
	<acme:message code="manager.workPlan.form.label.suggestion.format"/><br>
</jstl:if>