<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value="/j_spring_security_logout" var="logoutUrl" />

<head>
<style>
body {
	margin: 0;
	padding: 0;
	width: 100%;
}
.user-header div {
    display: inline-block;
}
a {
	color: #b2c8de;
    text-decoration: none;
}
a:hover {
	color: #ffffff;
}
.logout-div {
    padding: 15px;
    position: fixed;
    right: 0;
    top: 0;
    z-index: 1050;
}
.user-header {
	background: #222222 none repeat scroll 0 0;
	color: #f6f6f6;
	height: 50px;
	position: fixed;
	top: 0;
	width: 100%;
	z-index: 1050;
}
.page-head-text {
	font-family: caption;
	font-size: 20px;
	font-weight: bold;
	padding: 15px;
}
.content-div {
	height: inherit;
	margin-top: 50px;
	width: inherit;
}
.main-content {
	background: #F6F6F6 none repeat scroll 0 0;
	padding: 38px 10px 10px;
	text-align: center;
}
.error-msg {
	color: red;
}
</style>
</head>

<form action="${logoutUrl}" method="post" id="logoutForm">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</form>

<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>

<c:if test="${pageContext.request.userPrincipal.name != null}">
<div class="logout-div"> 
	<a href="javascript:formSubmit()"> Logout </a>
</div>

</c:if>