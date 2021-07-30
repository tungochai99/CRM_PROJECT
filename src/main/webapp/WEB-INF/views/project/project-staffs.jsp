<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="crmapp.utils.UrlConst" %>
<head>
<meta charset="UTF-8">
<title>Project Dashboard</title>
</head>
<body>
	<!-- Breadcrumb -->
		<div class="container page__heading-container">
		    <div class="page__heading">
		        <div class="d-flex align-items-center">
		            <div>
		                <nav aria-label="breadcrumb">
		                    <ol class="breadcrumb mb-0">
		                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.HOME %>" />">Home</a></li>
		                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.PROJECT_DASHBOARD %>"/>">Project Staffs</a></li>
		                        <li class="breadcrumb-item active" aria-current="page">
		                            Project Staff
		                        </li>
		                    </ol>
		                </nav>
		                <h1 class="m-0">Project Staff</h1>
		            </div>
		            <div class="ml-auto">
		                <a href="<c:url value="<%=UrlConst.PROJECT_STAFF_ADD %>" />" class="btn btn-light"><i class="material-icons icon-16pt text-muted mr-1">add</i>
		    				Add New Staff
		    			</a>
		            </div>
		        </div>
		    </div>
		</div>
	<!-- End Breadcrumb -->
	
	<!-- START BODY -->
		<div class="container">
			<div class="card card-form">
	            <table class="table mb-0 thead-border-top-0">
	                <thead>
	                    <tr>
							<th>Project Id</th>
		                     <th>User Id</th>
		                     <th>Join Date</th>
		                     <th>Role Description</th>
		                     <th>###</th>
	                    </tr>
	                </thead>
	                <tbody class="list" id="staff02">
	                 	<c:choose> 
	                 		<c:when test="${projectStaffs == null}">
	                 			<tr class="row">
	                 				<td class="col-12 text-center">There is no data.</td>
	                 			</tr>
	                 		</c:when>
	                 		<c:otherwise>
	                 			<c:forEach var="projectStaffs" items="${projectStaffs}" >
		                 			<tr>
		                 				<td>${projectStaffs.projectId }</td>
			                           <td>${projectStaffs.userId }</td>
			                           <td>${projectStaffs.joinDate }</td>
			                           <td>${projectStaffs.roleDescription }</td>
			                           <td>
			                           	<a href="<c:url value="<%=UrlConst.PROJECT_UPDATE%>" />?id=${projectStaff.id}" class="text-muted"><i class="material-icons">update</i></a>
			                           	<a href="<c:url value="<%=UrlConst.PROJECT_DELETE%>" />?id=${projectStaff.id}" class="text-muted"><i class="material-icons">delete</i></a>
			                           </td>
		                    		</tr>
	                 			</c:forEach>
	                 		</c:otherwise>
	                 	</c:choose>
	               	</tbody>
	            </table>
			</div>
		</div>
	<!-- END BODY -->
</body>