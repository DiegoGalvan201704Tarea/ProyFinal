<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="b" tagdir="/WEB-INF/tags/bootstrap" %>
<b:base title="Log In">
    
<script>
$(document).ready(function(){
	
	$("#home").addClass("active");
	
});

</script>
      
	<div class="jumbotron">
        <h2>Welcome ${sessionScope.validUser.fullName}</h2>
        <p>Bienvenido al sistema de administracion de materiales para mobile</p>
      </div>

    
 </b:base>