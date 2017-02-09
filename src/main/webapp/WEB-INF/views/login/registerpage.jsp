<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>  
  
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
	<head>  
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户注册</title>
		<link rel="stylesheet" href="/testMavenPro/bootstrap-3.3.0/css/bootstrap.css" >
		<link rel="icon" href="/testMavenPro/images/pander.ico">
		<style type="text/css">
			.color-red{
				color: red;
			}
			.vertical-center{
			  position: absolute;
			  top: 40%;
			  left: 50%;
			  transform: translate(-50%, -50%);
			}
		</style>
	</head>  
	<body>
	    
	    <div class="container">
	    	<div class="row">
	    		<div class="col-md-4 vertical-center">
	    			<h3 class="text-center">用户注册</h3>
	    			<form class="form-horizontal" action="../auth/register.html" method="post" role="form">
						<div class="form-group">
							<div class="col-md-12">
							  <input name="userName" class="form-control" id="userName" placeholder="邮箱">
							</div>
						</div>
						<div class="form-group">
						  <div class="col-md-12">
						    <input type="password" name="password" class="form-control" id="password" placeholder="密码">
						  </div>
						</div>
						<div class="form-group">
						  <div class="col-md-12">
						    <input type="password" class="form-control" id="confirmPassword" placeholder="确认密码">
						  	<span class="help-block color-red">${error}</span>
						  </div>
						</div>
						<div class="form-group">
						  <div class="col-md-offset-4 col-md-8">
						  	<button type="submit" class="btn btn-primary">注册</button>
						  	<a href="../auth/login.html" class="btn btn-default">登录</a>
						  </div>
						</div>
					</form>
	    		</div>
	    	</div>
		</div>
	</body> 
</html>