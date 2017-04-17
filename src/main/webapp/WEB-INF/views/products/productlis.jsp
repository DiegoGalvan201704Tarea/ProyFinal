<%@taglib prefix="b" tagdir="/WEB-INF/tags/bootstrap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<b:base title="List product">
<style>
  label, input { display:block; }
  input.text { margin-bottom:12px; width:95%; padding: .4em; }
  fieldset { padding:0; border:0; margin-top:25px; }
  h1 { font-size: 1.2em; margin: .6em 0; }
  div#users-contain { width: 350px; margin: 20px 0; }
  div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
   div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
  .ui-dialog .ui-state-error { padding: .3em; }
  .validateTips { border: 1px solid transparent; padding: 0.3em; }
</style>
      <script>
$(document).ready(function(){
	$("#listuser").addClass("active");
	$( "#dialog-form" ).dialog({
		  autoOpen: false,
	      height: 330,
	      width: 350,
	      modal: true,
	      buttons: {
	        Cancel: function() {
	        	$( "#dialog-form" ).dialog( "close" );
	        	
	        	
	            }
          
		}
	});
	
});

function getuser(username){
	$.get("http://localhost:8090/utm/api/v1/user/"+username).done(function(a){
		$("#fullName").html(a.fullName);
		$("#username").html(a.username);
		$("#password").html(a.password);
		$( "#dialog-form" ).dialog( "open" );
	});
}

function borrar(username){
	$.ajax({
		url:"http://localhost:8090/utm/api/v1/user/"+username,
		type:"DELETE",
		async: false
	});
	location.reload();
}

</script>  
      <!-- Main component for a primary marketing message or call to action -->
	<div>
		<h2>List Productos</h2>
		<div>
			<table class="table table-striped">
					<tr>
						<th>Name</th>
						<th>User</th>
						<th></th>
						
					</tr>
					<c:forEach items="${users}" var="element">
						<tr>
							<td><a onclick="getuser('${element.getUsername()}'); return false;">${element.getUsername()}</a></td>
							<td>${element.getFullName()}</td>
							<td><a onclick="borrar('${element.getUsername()}'); return false;">borrar</a></td>
	
						</tr>
					</c:forEach>
				</table>
		</div>
	</div>
	<div id="dialog-form" title="User Data">
	  <p class="validateTips">User data.</p>
	
			<label>Name: </label>
			<div id="fullName"></div>
			<label> Username: </label>
			<div id="username"></div>
			<label>Password:</label>
			<div  id="password"></div>
			
			
		
	     
	  </div>
</b:base>