<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page session="true"%>
<html>
<head>
<title>Login Page</title>
<style>
body {
    margin: 0;
    padding: 0;
	height: 100%;
	width: 100%;
}
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
.user-header {
	background: #222222 none repeat scroll 0 0;
    color: #b2c8de;
    height: 22px;
    padding: 8px 55px;
    position: relative;
    text-align: right;
}
.page-head-text {
	font-weight: bold;
    text-align: center;
    vertical-align: middle;
    width: 100%;
}
.content-div {
	height: inherit;
    margin-top: -38px;
    width: inherit;
}
.main-content {
	background: #F6F6F6 none repeat scroll 0 0;
    padding: 38px 10px 10px;
    text-align: center;
}
</style>
</head>
<body onload='document.loginForm.username.focus();'>
	<div class="user-header">
		<div class="page-head-text">VFusion Studio</div>
	</div>
	<div class="content-div">
		<div class="main-content">
		<h1>Welcome To VFusion Studio</h1>
		<sec:authorize access="isAuthenticated()" var="isAuthenticated">
			<%@include file="logout.jsp" %>			
		</sec:authorize>
		<c:if test="${not isAuthenticated}">
			  <div id="login-box">

				<h3>Login</h3>

				<c:if test="${not empty error}">
					<div class="error">${error}</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
				</c:if>

				<form name='loginForm'
					action="<c:url value='/j_spring_security_check' />" method='POST'>

					<table>
						<tr>
							<td>User:</td>
							<td><input type='text' name='username'></td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><input type='password' name='password' /></td>
						</tr>
						<tr>
							<td colspan='2'><input name="submit" type="submit"
								value="submit" /></td>
						</tr>
					</table>

					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />

				</form>
			</div>
		</c:if>
		</div>
	</div>
</body>
</html>