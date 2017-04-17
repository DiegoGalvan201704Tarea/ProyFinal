<%@taglib prefix="b" tagdir="/WEB-INF/tags/bootstrap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<b:base title="Upload Complete">
    <script>
$(document).ready(function(){
	
	$("#upload").addClass("active");
	
});

</script>  
      <!-- Main component for a primary marketing message or call to action -->
	<div>
		<h2>Upload complete</h2>
		<div>
			<c:if test="${not empty warnings }">
				<c:forEach var="warning" items="${warnings }">
					<div class="alert alert-info" role="alert">${warning }</div>
				</c:forEach>
			</c:if>
			<c:if test="${not empty errors }">
				<c:forEach var="error" items="${errors }">
					<div class="alert alert-danger" role="alert">${error }</div>
				</c:forEach>
			</c:if>
		</div>
	</div>

    
 </b:base>