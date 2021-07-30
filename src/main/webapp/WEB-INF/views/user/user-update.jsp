<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="crmapp.utils.UrlConst" %>
<!DOCTYPE html>
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
	                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.USER_DASHBOARD %>" />">User</a></li>
	                        <li class="breadcrumb-item active" aria-current="page">
	                            Update User
	                        </li>
	                    </ol>
	                </nav>
	                <h1 class="m-0">Update User</h1>
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
                    <form action="<c:url value="<%=UrlConst.USER_UPDATE %>" />" method="post">
                    	<div class="form-group">
				         <label class="font-weight-bold" for="code">Code</label>
				         <input type="text" class="form-control" name="id" id="id" aria-describedby="helpId" value="${user.id }" readonly
				         />
				       </div>
                        <div class="form-group">
                            <label class="font-weight-bold" for="email">Email:</label>
                            <input type="email" class="form-control" name ="email" id="email" value="${user.email }">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold" for="password">Password:</label>
                            <input type="password" class="form-control" name="password" id="password" value="${user.password }">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold" for="name">Name:</label>
                            <input type="text" class="form-control" name="name" id="name" value="${user.name }">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold" for="phone">Phone:</label>
                            <input type="text" class="form-control" name="phone" id="phone" value="${user.phone }">
                        </div>
                         <div class="form-group">
                            <label class="font-weight-bold" for="address">Address:</label>
                            <textarea type="text" class="form-control" name="address" id="address" value="${user.address }" aria-label="With textarea"></textarea>
                        </div>
                        <div class="form-group">
                                <label class="font-weight-bold" for="role">Role</label>
                                <select id="role" name="role" data-toggle="select" class="form-control">
                                    <option selected="" value="1">ADMIN</option>
                                    <option value="2">LEADER</option>
                                    <option value="3">STAFF</option>
                                </select>
                            </div>
                        <button class="btn btn-primary w-25 justify-content-center" type="submit" class="btn btn-primary">Upadte</button>
                    </form>
                </div>
            </div>
        </div>
     </div>
</body>
</html>