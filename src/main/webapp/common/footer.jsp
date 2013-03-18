<%@ include file="/common/taglibs.jsp" %>

	<br>
    <span class="left"><fmt:message key="webapp.version"/>

        <c:if test="${pageContext.request.remoteUser != null}">
         | <fmt:message key="user.status"/> ${pageContext.request.remoteUser}
        </c:if>
    </span>
