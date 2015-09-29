<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>User Page</title>
</head>
<body>
	<div class="user-header">
		<div> Hi ${pageContext.request.userPrincipal.name} &nbsp;|&nbsp; </div>
		<div><%@include file="logout.jsp" %></div>
	</div>
	<div class="content-div">
		<div class="main-content"><h1>This is the ${message}</h1></div>
	</div>
</body>
</html>