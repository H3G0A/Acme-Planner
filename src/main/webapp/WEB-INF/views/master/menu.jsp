<%@page language="java" import="acme.framework.helpers.PrincipalHelper"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.tasks" action="/anonymous/task/list"/>
			<acme:menu-suboption code="master.menu.anonymous.listShouts" action="/anonymous/shout/list"/>
			<acme:menu-suboption code="master.menu.anonymous.createShouts" action="/anonymous/shout/create"/>
			<acme:menu-suboption code="master.menu.anonymous.listWorkPlans" action="/anonymous/work-plan/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.authenticated.tasks" action="/authenticated/task/list"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.spam-word.list" action="/administrator/word/list"/>
			<acme:menu-suboption code="master.menu.administrator.spam-word.create" action="/administrator/word/create"/>
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.task-dashboard" action="/administrator/task-dashboard/show"/>
			<acme:menu-suboption code="master.menu.administrator.dashboard-workplans" action="/administrator/workplan-dashboard/show"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/master/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/master/populate-sample"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown"/>
		</acme:menu-option>

		
		<acme:menu-option code="master.menu.manager" access="hasRole('Management')">
			<acme:menu-suboption code="master.menu.manager.task" action="/management/task/list"/>
			<acme:menu-suboption code="master.menu.manager.workplan" action="/management/work-plan/list"/>
			<acme:menu-suboption code="master.menu.manager.createTask" action="/management/task/create"/>
			<acme:menu-suboption code="master.menu.manager.create.workPlan" action="/management/work-plan/create"/>
		</acme:menu-option>
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-manager" action="/authenticated/management/create" access="!hasRole('Management')"/>
			<acme:menu-suboption code="master.menu.user-account.manager" action="/authenticated/management/update" access="hasRole('Management')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

