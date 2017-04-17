<%@taglib prefix="b" tagdir="/WEB-INF/tags/bootstrap" %>
<b:base title="Upload File">
   
    <script>
$(document).ready(function(){
	
	$("#upload").addClass("active");
	
});

</script>  
      <!-- Main component for a primary marketing message or call to action -->
		<form method="POST" action="upload" enctype="multipart/form-data">
				<div class="form-group">
					 <label for="name">Name</label>
    				 <input type="text" class="form-control" id="name" 
    				 placeholder="File name" name="name" required="true">
				</div>
				<div class="form-group">
					 <label for="path">Path</label>
    				 <input type="text" class="form-control" id="path" 
    				 placeholder="File path" name="path" required="true">
				</div>
				<div class="form-group">
					<label for="exampleInputFile">File input</label>
				    <input type="file" id="file" name="file"  required="true">
				    <p class="help-block">This file will be uploaded</p>
				</div>
			<button type="submit" class="btn btn-default">Upload</button>
		    </form>

    
 </b:base>