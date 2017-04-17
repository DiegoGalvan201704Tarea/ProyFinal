<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="b" tagdir="/WEB-INF/tags/bootstrap" %>
<b:base title="List">
  <script>
$(document).ready(function(){
	
	$("#listfile").addClass("active");
	
});

</script>  

      <!-- Main component for a primary marketing message or call to action -->
		<form method="POST" action="list/show">
				<div class="form-group">
					 <label for="path">Path</label>
    				 <input type="text" class="form-control" id="path" 
    				 placeholder="Path to list" name="path" required="true">
				</div>
			<button type="submit" class="btn btn-default">List</button>
		    </form>

    
 </b:base>