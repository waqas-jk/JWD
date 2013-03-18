<%@page import="org.jwd.assignment.Constants"%>
<%@page import="org.jwd.assignment.model.Role"%>
<%@page import="org.jwd.assignment.model.User"%>
<%@ include file="/common/taglibs.jsp"%>

<%
	boolean isAdmin = request.isUserInRole(Constants.ADMIN_ROLE);
%>
<c:set  var="isAdmin" ><%=isAdmin%> </c:set>
<c:if test="${not empty pageContext.request.remoteUser }">  
<div class="nav-collapse collapse">
<ul style="list-style-type: none;" >
     
     <c:if test="${isAdmin}">
	    <li style="display:inline;">
	        <a href="<c:url value="/admin/users.action"/>">Users</a>
	    </li>
	    <li style="display:inline;">   |  </li>
    </c:if>
    
    <li style="display:inline;">
        <a href="<c:url value="/logout.action"/>">Logout</a>
    </li>
</ul>
</div>
 </c:if>