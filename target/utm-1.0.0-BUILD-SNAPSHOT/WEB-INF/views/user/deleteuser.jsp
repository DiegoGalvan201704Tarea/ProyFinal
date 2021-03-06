<%@taglib prefix="b" tagdir="/WEB-INF/tags/bootstrap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<b:base title="Delete user">
        <script>
$(document).ready(function(){
	
	$("#deleteuser").addClass("active");
	
});

</script>
      <!-- Main component for a primary marketing message or call to action -->
	<div>
		<h2>Delete User</h2>
		<div>
			<form:form method="post" modelAttribute="user">
			
			<form:label path="username">Username</form:label>
			<form:input path="username" cssClass="form-control" placeholder="Username"/>
			
			<br/>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Delete User</button>
		</form:form>
		</div>
		<div>
			<c:if test="${isError }">
				
					<div class="alert alert-info" role="alert">${error}</div>
				
			</c:if>
		</div>
	</div>
</b:base>