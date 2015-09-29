<%@page session="false"%>
<html>
<head>
<title>Editor Page</title>
</head>
<body>
	<div class="user-header">
		<div class="page-head-text">Editor Page</div>		
		<div> Hi ${pageContext.request.userPrincipal.name} &nbsp;|&nbsp; </div>
		<div><%@include file="logout.jsp" %></div>
	</div>
	<div class="content-div">
		<div class="main-content">
			<h1>This is the Editor Page</h1>
		</div>
	</div>
</body>
</html>