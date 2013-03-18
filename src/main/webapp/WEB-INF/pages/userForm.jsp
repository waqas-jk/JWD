<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="userProfile.title"/></title>
    <meta name="menu" content="UserMenu"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="userList.user"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="userProfile.heading"/></h2>
            <p><fmt:message key="userProfile.message"/></p>
</div>
<div class="span7">
    <s:form name="userForm" action="saveUser" method="post" validate="true" cssClass="well form-horizontal" autocomplete="off">
        <s:hidden key="user.id"/>
        <s:hidden key="user.enabled" />
        <s:textfield key="user.username" required="true"/>
			
            <s:password key="user.password" showPassword="true" required="true"
                        onchange="passwordChanged(this)"/>

            <s:password key="user.confirmPassword" required="true" 
                        showPassword="true" onchange="passwordChanged(this)"/>
        <s:textfield key="user.firstName" required="true"/>
        <s:textfield key="user.lastName" required="true"/>
        <s:textfield key="user.email" required="true"/>

        <fieldset class="control-group">
            <label for="userRole" class="control-label"><fmt:message key="userProfile.assignRoles"/></label>
            <div class="controls">
            	  <select id="userRole" name="userRole">
                    <c:forEach items="${availableRoles}" var="role">
                    <option value="${role.name}" ${fn:contains(user.authority.name, role.name) ? 'selected' : ''}>${role.name}</option>
                    </c:forEach>
                </select>
           </div>
        </fieldset>
        <div id="actions" class="form-actions">
            <s:submit type="button" cssClass="btn btn-primary" method="save" key="button.save" theme="simple">
                <i class="icon-ok icon-white"></i>
                <fmt:message key="button.save"/>
            </s:submit>
            <c:if test="${not empty user.id}">
                <s:submit type="button" cssClass="btn btn-danger" method="delete" key="button.delete"
                    onclick="return confirmMessage(msgDelConfirm)" theme="simple">
                    <i class="icon-trash"></i>
                    <fmt:message key="button.delete"/>
                </s:submit>
            </c:if>
            <s:submit type="button" cssClass="btn" method="cancel" key="button.cancel" theme="simple">
                <i class="icon-remove"></i>
                <fmt:message key="button.cancel"/>
            </s:submit>
        </div>
    </s:form>
</div>

<c:set var="scripts" scope="request">

<script type="text/javascript">

    function passwordChanged(passwordField) {
        if (passwordField.name == "user.password") {
            var origPassword = "<s:property value="user.password"/>";
        } else if (passwordField.name == "user.confirmPassword") {
            var origPassword = "<s:property value="user.confirmPassword"/>";
        }

        if (passwordField.value != origPassword) {
            createFormElement("input", "hidden", "encryptPass", "encryptPass",
                    "true", passwordField.form);
        }
    }
</script>
</c:set>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['userForm']).focus();
    });
</script>
