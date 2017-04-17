<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="b" tagdir="/WEB-INF/tags/bootstrap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<b:base title="List Complete">
     <script>
$(document).ready(function(){
	
	$("#listfile").addClass("active");
	
});

</script>  

      <!-- Main component for a primary marketing message or call to action -->
	<div>
		<ol class="breadcrumb">
         <li><a href="${pageContext.request.contextPath}/list">Download</a></li>
 		 <li class="active">${path }</li>
	  </ol>
		<c:if test="${fn:length(requestScope.paths) gt 0}">
			<table class="table table-striped">
					<tr>
						<th>Name</th>
						<th>Path</th>
						<th>Size (B)</th>
					</tr>
					<c:forEach items="${paths}" var="element">
						<tr>
							<td><a href="files?fileName=${element}">${element.getFileName()}</a></td>
							<td>${element}</td>
							<td>${element.toFile().length() }</td>
	
						</tr>
					</c:forEach>
				</table>
		</c:if>
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

    
 </b:base>