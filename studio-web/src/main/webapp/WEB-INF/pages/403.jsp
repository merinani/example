<html>
<head>
<title>Error Page</title>
</head>
<body>
	<div class="user-header">
		<div class="page-head-text">HTTP Status 403 - Access is denied</div>
		<div><%@include file="logout.jsp" %></div>
	</div>
	<div class="content-div">
		<div class="main-content">
			<h1 class="error-msg">${msg}</h1>
		</div>
	</div>
</body>
</html>