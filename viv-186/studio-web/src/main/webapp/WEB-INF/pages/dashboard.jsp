<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project Dashboard</title>
<!--<link rel="stylesheet" type="text/css" href="/resources/js/jquery/jquery.dataTables.min.css">
<script type="text/javascript" src="/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/resources/js/jquery/jquery.dataTables.min.js"></script>-->
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.0/css/jquery.dataTables.css">
<script type="text/javascript" src="//code.jquery.com/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="//cdn.datatables.net/1.10.0/js/jquery.dataTables.js"></script>
<script type="text/javascript">


$(document).ready(function() {

	$("#projectDetailsTable").dataTable( {
        "aaSorting": [[ 0, 'asc' ]],
        // For display of Processing... inducator
        "bProcessing": true,
        // Configure DataTables to use server-side processing
        "bServerSide": true,
        // bStateSave variable you can use to save state on client cookies: set value "true" 
        "bStateSave": false,
        // Default: Page display length
        "iDisplayLength": 10,
        // Define starting point of data display
        "iDisplayStart": 0,
        "fnDrawCallback": function () {
            
        },
        // DataTables load data from external source when bServerSide = true         
        "sAjaxSource": "user",
        // mData property used to read data from JSON data source
        "aoColumns": [
            { "mData": "projectName" },
            { "mData": "version" },
            { "mData": "lastUpdated" },
            { "mData": "lastUpdatedUser" },
        ]
    } );

	
	$('#projectDetailsTable tbody').on( 'click', 'tr', function (event) {
		var currentUserHasAccess = $("#projectDetailsTable").dataTable().fnGetData()[event.currentTarget.rowIndex - 1].currentUserHasAccess;
		location.href = currentUserHasAccess ? "./editor" : "./403";
	});


} );

</script>
</head>
<body>

<div class="user-header">
	<div> Hi ${pageContext.request.userPrincipal.name} &nbsp;|&nbsp; </div>
	<c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
		<div> <a href="<c:url value="/manage" />">Manage</a> &nbsp;|&nbsp;</div>
	</c:if>
	<div><%@include file="logout.jsp" %></div>
</div>


<form:form action="" method="GET">
<div class="content-div">
	<div class="main-content">
		<table width="70%" style="border: 3px;background: rgb(243, 244, 248);"><tr><td>
			<table id="projectDetailsTable" class="display" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th>PROJECT NAME</th>
     					<th>VERSION</th>
     					<th>LAST UPDATED</th>
     					<th>LAST UPDATED USER</th>
					</tr>
				</thead>       
			</table>
		</td></tr></table>
	</div>
</div>

</form:form>
</body>
</html>
