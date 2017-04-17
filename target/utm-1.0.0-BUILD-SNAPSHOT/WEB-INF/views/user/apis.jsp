<%@taglib prefix="b" tagdir="/WEB-INF/tags/bootstrap" %>

<b:base title="Apis Rest users">
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
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};
 var dialog, form;
$(document).ready(function(){
	$("#apis").addClass("active");
	$( "#dialog-form" ).dialog({
		  autoOpen: false,
	      height: 450,
	      width: 350,
	      modal: true,
	      buttons: {
	       	"Create an account": addUser,
	        Cancel: function() {
	        	$( "#dialog-form" ).dialog( "close" );
	        	 $("#formaprincipal").reset();
	              $("input").removeClass( "ui-state-error" );
	        	
	            }
            
		}
	});
	listusers();
 
	
      
        
});

function agregar(){
	$( "#dialog-form" ).dialog( "open" );
}
function addUser(){
	var formseri = $("#dialog-form").find("form").serialize();
	var fn = $('#fullName').val();
	var username = $('#username').val();
	var pass = $('#password').val();

	formseri.submit();
	/*
	$.ajax({
		url:'http://localhost:8090/utm/api/v1/user/'+username,
		contentType: "application/json",
		dataType:'json',
		data:	
		{
			"fullName":fn,
			"password":pass,
			"username":username
		},
		
		type: "post",
		async: false,
		success: function(a){
			alert("respondio ok");
		},
		error:function(a,b,c){
			  $("#error").html(a.responseText);
		
		}
	});
	*/
	//var t = $("#dialog-form").find("form").ajaxSubmit({url: 'http://localhost:8090/utm/api/v1/user/'+username, type: 'post'})
	$("#dialog-form").find("form").trigger("reset");
	$( "#dialog-form" ).dialog( "close" );
	listusers();
	
	 
	}
	function borrar(username){
		$.ajax({
			url:"http://localhost:8090/utm/api/v1/user/"+username,
			type:"DELETE",
			async: false
		});
		listusers();
	}
	function listusers(){
		$("tbody").empty();
		
		$.ajax({
			url:"http://localhost:8090/utm/api/v1/user/",
			type:"GET",
			cache: false,
	        dataType: "xml",
	        success: function(xml) {
	        	var adds;
	        	 
                $(xml).find('user').each(function(){
                	
                	adds = adds+"<tr><td>"+$(this).find("fullName").text()+"</td>";
                	adds = adds+"<td>"+$(this).find("username").text()+"</td>";
                	//
                	adds = adds+"<td><a onclick='borrar(\""+$(this).find("username").text()+"\");return false;'>borrar</a></td></tr>";
                });
                
                $("#tblcuerpo").html(adds);
            }
		});
	}

</script> 
      <!-- Main component for a primary marketing message or call to action -->
	<div>
		<h2>Apis Rest User</h2>
		<div>
		<button id="create-user" onclick="agregar();return false;">Create new user</button>
		
			<table class="table table-striped">
					<thead>
					<tr>
						<th>Name</th>
						<th>User</th>
						<th></th>
						
					</tr>
					</thead>
					<tbody id ="tblcuerpo">
					
					</tbody>
				</table>
		</div>
	</div>
	
	<div id="dialog-form" title="Create new user">
	  <p class="validateTips">All form fields are required.</p>
	   <form:form method="post" modelAttribute="user" >
			<form:label path="fullName">Name</form:label>
			<form:input  path="fullName" cssClass="form-control" placeholder="fullName"/>
			<form:label path="username">Username</form:label>
			<form:input  path="username" cssClass="form-control" placeholder="Username"/>
			<form:label  path="password">Password</form:label>
			<form:password  path="password" cssClass="form-control" placeholder="****"/>
			
			
		</form:form>
	     
	  </div>
	  <div id="error"></div>
</b:base>