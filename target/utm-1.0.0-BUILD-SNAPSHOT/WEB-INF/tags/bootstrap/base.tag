<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" type="java.lang.String" rtexprvalue="true" required="true" %>
<%@ attribute name="lang" type="java.lang.String" rtexprvalue="true" required="false" %>
<%@ include file="/WEB-INF/includes/base.jspf" %>

<!DOCTYPE html>
<html lang="${not empty lang ? lang : 'en_US'}">
	<title>${title}</title>
    <head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
		 integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" 
		 crossorigin="anonymous">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
		 integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
		  crossorigin="anonymous">
		  <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
        <title><c:out value="${fn:trim(htmlTitle)}" /></title>
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
         <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" 
		integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" 
		crossorigin="anonymous"></script>
		
    </head>
    <body>
    <div class="container">
    	<!-- Static navbar -->
      <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Project name</a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li id="home"><a href="${pageContext.request.contextPath}">Home</a></li>
              <li id="upload"><a href="${pageContext.request.contextPath}/upload">Upload</a></li>
              <li id="listfile"><a href="${pageContext.request.contextPath}/list">Download</a></li>
              <li id="listuser"><a href="${pageContext.request.contextPath}/user/userlist">List User</a></li>
              <li id="createuser"><a href="${pageContext.request.contextPath}/user/createuser">Create User</a></li>
              <li id="deleteuser"><a href="${pageContext.request.contextPath}/user/deleteuser">Delete User</a></li>
              <li id="apis"><a href="${pageContext.request.contextPath}/user/apis">Apis RESTFull User</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
              <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>
        <jsp:doBody />
        </div> <!-- /container -->
       
    </body>
</html>