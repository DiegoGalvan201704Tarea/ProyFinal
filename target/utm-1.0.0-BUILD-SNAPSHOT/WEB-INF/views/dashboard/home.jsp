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
        <p>This example is a quick exercise to illustrate how the default, static navbar and fixed to top navbar work. It includes the responsive CSS and HTML, so it also adapts to your viewport and device.</p>
      </div>

    
 </b:base>