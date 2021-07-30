<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="crmapp.utils.UrlConst" %>
<html>
<head>
<meta charset="UTF-8">
<title>Update User Page</title>
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
	                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.PROJECT_DASHBOARD %>" />">Project</a></li>
	                        <li class="breadcrumb-item active" aria-current="page">
	                            Update Project
	                        </li>
	                    </ol>
	                </nav>
	                <h1 class="m-0">Update Project</h1>
	            </div>
	        </div>
	    </div>
	</div>
	<!-- End Breadcrumb -->
	<div class="container page__container">
		<div class="card card-form">
            <div class="row no-gutters">
                <div class="col-lg-4 card-body">
                    <p><strong class="headings-color">Rules</strong></p>
                    <p class="text-muted">There is no rule!</p>
                </div>
                <div class="col-lg-8 card-form__body card-body">
                    <form action="<c:url value="<%=UrlConst.PROJECT_UPDATE %>" />" method="post">
                    	<div class="form-group">
				         <label class="font-weight-bold" for="Id">Id</label>
				         <input type="text" class="form-control" name="id" id="id" aria-describedby="helpId" value="${project.id }" readonly
				         />
				       </div>
                        <div class="form-group">
                            <label class="font-weight-bold" for="description">Description:</label>
                            <input type="text" class="form-control" name ="description" id="description" value="${project.name }">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold" for="startDate">Start Date:</label>
                            <input type="Date" class="form-control" name="startDate" id="startDate" value="${project.startDate }">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold" for="endDate">End Date:</label>
                            <input type="date" class="form-control" name="endDate" id="endDate" value="${project.endDate }">
                        </div>
                        
                         <div class="form-group">
                            <label class="font-weight-bold" for="owner">Owner:</label>
                            <input type="text" class="form-control" name="owner" id="owner" value="${project.id }">
                        </div>
                        <button class="btn btn-primary w-25 justify-content-center" type="submit" class="btn btn-primary">Upadte</button>
                    </form>
                </div>
            </div>
        </div>
     </div>
</body>
</html>