<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="userList.title"/></title>
    <meta name="menu" content="AdminMenu"/>
</head>

<div class="span10">
    <h2><fmt:message key="userList.heading"/></h2>

    <display:table name="users" cellspacing="0" cellpadding="0" requestURI=""
                   defaultsort="1" id="users" pagesize="25" class="table table-condensed table-striped table-hover" export="false">
        <display:column property="username" escapeXml="true" sortable="true" titleKey="user.username" style="width: 25%"
                        url="/editUser.action" paramId="id" paramProperty="id"/>
        <display:column property="fullName" escapeXml="true" sortable="true" titleKey="activeUsers.fullName"
                        style="width: 34%"/>
        <display:column property="email" sortable="true" titleKey="user.email" style="width: 25%" autolink="true"
                        media="html"/>
        <display:column property="email" titleKey="user.email" media="csv xml excel pdf"/>
        <display:column sortProperty="enabled" sortable="true" titleKey="user.enabled"
                        style="width: 16%; padding-left: 15px" media="html">
            <input type="checkbox" disabled="disabled" <c:if test="${users.enabled}">checked="checked"</c:if>/>
        </display:column>
        <display:column property="enabled" titleKey="user.enabled" media="csv xml excel pdf"/>

        <display:setProperty name="paging.banner.item_name"><fmt:message key="userList.user"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="userList.users"/></display:setProperty>
    </display:table>
    <div id="actions" class="form-actions">
    	<ul style="list-style-type: none;">
	        <LI style="display:inline;"> 
	        	<a class="btn btn-primary" href="<c:url value='/editUser.action?method=Add'/>" >
	            	<i class="icon-plus icon-white"></i> <fmt:message key="button.add"/>
	        	</a>
	        </LI>
	        <li style="display:inline;">   |  </li>
	        <LI style="display:inline;">
		        <a class="btn" href="<c:url value="/mainMenu.action"/>">
		            <i class="icon-ok"></i> <fmt:message key="button.done"/>
		        </a>
	        <LI>
        </ul>
    </div>
</div>
