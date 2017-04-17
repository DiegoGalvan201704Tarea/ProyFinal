<%@taglib prefix="b" tagdir="/WEB-INF/tags/bootstrap" %>

<b:base title="Productos">
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
	
	//var formseri = $("#dialog-form").find("form").serialize();
	//var fn = $('#fullName').val();
	//var username = $('#username').val();
	//var pass = $('#password').val();

	//formseri.submit();
	
	$("#formaproductos").submit();
	
	//var t = $("#dialog-form").find("form").ajaxSubmit({url: 'http://localhost:8090/utm/api/v1/user/'+username, type: 'post'})
	$("#dialog-form").find("form").trigger("reset");
	$( "#dialog-form" ).dialog( "close" );
	listusers();
	
	 
	}
	function borrar(id){
		var respuesta = confirm("Esta seguro en borrar este producto?");
		
		if(respuesta == true){
			$.ajax({
				url:"http://localhost:8090/utm/api/v1/product/"+id,
				type:"DELETE",
				async: false
			});
			listusers();
		}
	}
	function listusers(){
		$("tbody").empty();
		
		$.ajax({
			url:"http://localhost:8090/utm/api/v1/product/",
			type:"GET",
			cache: false,
	        dataType: "xml",
	        success: function(xml) {
	        	var adds;
	        	 
                $(xml).find('product').each(function(){
                	
                	adds = adds+"<tr><td>"+$(this).find("folio").text()+"</td>";
                	adds = adds+"<td>"+$(this).find("compuesto").text()+"</td>";
                	adds = adds+"<td>"+$(this).find("espesor").text()+"</td>";
                	//
                	adds = adds+"<td><a onclick='borrar(\""+$(this).find("id").text()+"\");return false;'>borrar</a></td></tr>";
                });
                
                $("#tblcuerpo").html(adds);
            }
		});
	}

</script> 
      <!-- Main component for a primary marketing message or call to action -->
	<div>
		<h2>Productos</h2>
		<div>
		<button id="create-user" onclick="agregar();return false;">Crear nuevo Producto</button>
		
			<table class="table table-striped">
					<thead>
					<tr>
						<th>Folio</th>
						<th>Compuesto</th>
						<th>Espesor</th>
						<th></th>
						
					</tr>
					</thead>
					<tbody id ="tblcuerpo">
					
					</tbody>
				</table>
		</div>
	</div>
	
	<div id="dialog-form" title="Create new Product">
	  <p class="validateTips">All form fields are required.</p>
	   <form:form method="post" modelAttribute="product" id="formaproductos" action="${pageContext.request.contextPath}/products/apis" >
			<form:label path="folio">Folio</form:label>
			<form:input  path="folio" cssClass="form-control" placeholder="Folio"/>
			<form:label path="compuesto">compuesto</form:label>
			<form:input  path="compuesto" cssClass="form-control" placeholder="Compuesto"/>
			<form:label path="espesor">espesor</form:label>
			<form:input  path="espesor" cssClass="form-control" placeholder="espesor"/>
			<form:label path="kgPieza">kgPieza</form:label>
			<form:input  path="kgPieza" cssClass="form-control" placeholder="kgPieza"/>
			<form:label path="piezasPaquetes">piezasPaquetes</form:label>
			<form:input  path="piezasPaquetes" cssClass="form-control" placeholder="piezasPaquetes"/>
			
			<form:input  path="id" cssClass="form-control" placeholder="0" style="display:none;"/>
			
			
		</form:form>
	     
	  </div>
	  <div id="error"></div>
</b:base>