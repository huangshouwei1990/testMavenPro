<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户列表</title>
	<link rel="stylesheet" href="/testMavenPro/bootstrap-3.3.0/css/bootstrap.css" >
	<link rel="icon" href="/testMavenPro/images/pander.ico">
	<script src="/testMavenPro/jquery-2.1.1/jquery.min.js"></script>
	<script src="/testMavenPro/bootstrap-3.3.0/js/bootstrap.min.js"></script>
	<script src="/testMavenPro/js/util.js"></script>
	<style type="text/css">
		.float-left{
			float:left;
		}
		.float-right{
			float:right;
		}
	</style>
</head>  
<body>  
    <div class="container">
    	<div class="row">
    		<div class="col-md-offset-2 col-md-8">
    			<div class="row">
    				<div class="col-md-8">
    					<h3 class="text-left">用户列表</h3>
    				</div>
    				<div class="col-md-4">
    					<h6 class="text-right">
    						<a href="/testMavenPro/auth/registerPage.html">注册</a>
    						<a href="/testMavenPro/auth/logout.html">退出</a>
    					</h6>
    				</div>
    			</div>
	
				<!-- 成功提示框 -->
    			<div id="successAlert" class="row">
    				<div class="col-md-12">
    					<div class="alert alert-success alert-dismissible" role="alert">
							<button type="button" class="close" data-dismiss="alert">
								<span aria-hidden="true">&times;</span>
								<span class="sr-only">Close</span>
							</button>
							<strong>Well done!</strong>
							<span id="successMessage"></span>
						</div>
    				</div>
    			</div>

    			<!-- 错误提示框 -->
    			<div id="errorAlert" class="row">
    				<div class="col-md-12">
    					<div class="alert alert-danger alert-dismissible" role="alert">
							<button type="button" class="close" data-dismiss="alert">
								<span aria-hidden="true">&times;</span>
								<span class="sr-only">Close</span>
							</button>
							<strong>Oh snap!</strong>
							<span id="errorMessage"></span>
						</div>
    				</div>
    			</div>
    			
    			<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>序号</th>
							<th>图像</th>
							<th>邮箱</th>
							<th>密码</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${userList }" var="user" varStatus="index">
						<tr>
							<td>${index.count }</td>
							<td>
								<img width="50" height="50" src="${user.images[0].imageUrl }" alt="图像" class="img-circle"/>
							</td>
							<td>${user.userName }</td>
							<td>${user.password }</td>
							<td>
								<button class="btn btn-success btn-sm showUser" role="button" user_id="${user.id}">查看</button>
								<button class="btn btn-warning btn-sm updateUser" role="button" user_id="${user.id}">修改</button>
								<a href="/testMavenPro/user/deleteUserById/${user.id }.html" class="btn btn-danger btn-sm deleteUser" role="button" user_id="${user.id}">删除</a>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="row">
					<div class="col-md-4 text-left">
						<div class="dropdown">
							<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown">
								每页展示<span>${pageSize }</span>条
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
								<li role="presentation"><a role="menuitem" tabindex="-1" href="/testMavenPro/user/userList/1/2.html">2</a></li>
								<li role="presentation"><a role="menuitem" tabindex="-1" href="/testMavenPro/user/userList/1/5.html">5</a></li>
								<li role="presentation"><a role="menuitem" tabindex="-1" href="/testMavenPro/user/userList/1/10.html">10</a></li>
							</ul>
							<span>共${totalPage }页</span>
						</div>
					</div>
					<div class="col-md-8 text-right">
						<nav>
							<ul class="pagination" style="margin:0px;">
							   <c:if test="${currentPage == 1 }">
						   			<li class="disabled"><a href="#">«</a></li>
						   		</c:if>
						   		<c:if test="${currentPage != 1 }">
						   			<li><a href="/testMavenPro/user/userList/${currentPage-1 }/${pageSize }.html">«</a></li>
						   		</c:if>
							   <c:forEach items="${pageNumbers }" var="pageNumber">
									<c:if test="${currentPage == pageNumber }">
										<li class="active"><a href="/testMavenPro/user/userList/${currentPage }/${pageSize }.html">${currentPage }<span class="sr-only">(current)</span></a></li>	
									</c:if>
									<c:if test="${currentPage!=pageNumber }">
										<li><a href="/testMavenPro/user/userList/${pageNumber }/${pageSize }.html">${pageNumber }</a></li>	
									</c:if>
							   </c:forEach>
							   <c:if test="${currentPage ==  totalPage}">
									<li class="disabled"><a href="#">»</a></li>
								</c:if>
								<c:if test="${currentPage !=  totalPage}">
									<li><a href="/testMavenPro/user/userList/${currentPage + 1 }/${pageSize }.html">»</a></li>
								</c:if>
							</ul>
						</nav>
					</div>
				</div>
				
				<div class="row">
					<!-- 查看详情模态框 -->
					<div id="showUserModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="row">
									<div class="col-md-offset-2 col-md-6">
										<h3>用户详情</h3>
										<p class="text-left">用户名：<label id="show_userName"></label></p>
										<p class="text-left">密码：<label id="show_password"></label></p>
										<p class="text-left">创建时间：<label id="show_createTime"></label></p>
										<p class="text-left">修改时间：<label id="show_updateTime"></label></p>
									</div>
									<div class="col-md-2">
										<img style="margin:10px" class="img-circle" height="200px" width="200px" id="show_image" src="" alt="图像"/>
									</div>
								</div>
								<div class="row">
									<div class="col-md-offset-2 col-md-8">
										<h6>图片列表</h6>
										<div id="show_imageList"></div>
										<div>
											<p id="show_imageName"></p>
											<img style="margin:10px;max-width: 100%;" id="show_bigImage" src="" alt=""/>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<!-- 修改用户模态框 -->
					<div id="updateUserModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="row">
									<div class="col-md-offset-2 col-md-8">
										<h3>修改用户</h3>
										<form class="form-horizontal" action="/testMavenPro/user/updateUser.html" method="post" role="form" enctype="multipart/form-data">
											<div class="form-group">
												<div class="col-md-2">
													<label>用户名：</label>
												</div>
												<div class="col-md-8">
													<input name="id" class="hidden" id="idInput" value=""/>
													<input name="userName" class="form-control" id="userNameInput" placeholder="邮箱">
												</div>
											</div>
											<div class="form-group">
												<div class="col-md-2">
													<label for="exampleInputFile">上传图片:</label>
												</div>
											  	<div class="col-md-8">
											  		<input name="imageFile" type="file" id="exampleInputFile">
											  	</div>
											</div>
											<div class="form-group">
												<div class="col-md-2">
													<label for="exampleInputFile">图片列表:</label>
												</div>
											  	<div class="col-md-8" id="update_imageList"></div>
											  	<div class="col-md-offset-2 col-md-8">
											  		<div>
											  			<span id="update_imageName" class="float-left"></span>
											  			<button type="button" id="update_imageDeleteBtn" class="btn btn-danger hide btn-xs float-right">删除</button>
											  		</div>
											  		<img style="margin:10px;max-width: 100%;" id="update_imageDetail" src="" alt=""/>
											  	</div>
											</div>
											<div class="form-group">
											  <div class="col-md-offset-4 col-md-8">
											  	<button type="submit" class="btn btn-primary">修改</button>
											    <button type="button" id="cancelUpdateBtn" class="btn btn-default">取消</button>
											  </div>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
    		</div>
    	</div>
    </div>
    <script type="text/javascript">
	    function showBigImage(image_Url,image_Name){
			$("#show_bigImage").attr("src",image_Url);
			$("#show_bigImage").attr("alt",image_Name);
			$("#show_imageName").html(image_Name);
		}
	    
	    function showImageDetail(image_Url,image_Name){
			$("#update_imageDetail").attr("src",image_Url);
			$("#update_imageDetail").attr("alt",image_Name);
			$("#update_imageName").html(image_Name);
			$("#update_imageDeleteBtn").removeClass("hide");
		}
    	$(function(){
    		$("#errorAlert").hide();
    		$("#successAlert").hide();
    		var flag = getQueryParam("flag");
    		if(flag == "false"){
    			//var message = getQueryParam("message");
    			$("#errorMessage").html("操作失败！");
    			$("#errorAlert").fadeIn(600).delay(3000).fadeOut(600);
    		}
    		if(flag == "true"){
    			//var message = getQueryParam("message");
    			$("#successMessage").html("操作成功！");
    			$("#successAlert").fadeIn(600).delay(3000).fadeOut(600);
    		}
    		
    		//查看用户详情模态框
    		$(".showUser").click(function(){
    			$('#showUserModal').modal({
    				show:true
    			});
    			var userId = $(this).attr("user_id");
    			$.ajax({
				   type: "GET",
				   url: "/testMavenPro/user/json/userById/"+userId+".html",
				   dataType: "json",
				   contentType:"application/json;charset=UTF-8",//必须有
				   success: function(msg){
					   $("#show_userName").html(msg.userName);
					   $("#show_password").html(msg.password);
					   $("#show_createTime").html(new Date(msg.createTime).Format("yyyy-MM-dd hh:mm:ss"));
					   $("#show_updateTime").html(new Date(msg.lastUpdateTime).Format("yyyy-MM-dd hh:mm:ss"));
					   var images = msg.images;
					   if(images.length>0){
						   var imageName = images[0].imageName;
						   var imageUrl = images[0].imageUrl;
						   $("#show_image").attr("src",imageUrl);
						   $("#show_image").attr("alt",imageName);
						   
						   $("#show_imageList").html("");
						   for(var i=0;i<images.length;i++){
							   var image_Name = images[i].imageName;
							   var image_Url = images[i].imageUrl;
							   var html_str = '<image onclick="showBigImage(\''+image_Url+'\',\''+image_Name+'\')" style="margin:5px" class="img-circle" width="50px" height="50px" src="'+image_Url+'" alt="'+image_Name+'"/>';
							   $("#show_imageList").append(html_str);
						   }
					   }
				   }
   				});
    		});

    		
    		//更新用户详情模态框
    		$(".updateUser").click(function(){
    			$('#updateUserModal').modal({
    				show:true
    			});
    			var userId = $(this).attr("user_id");
    			$.ajax({
 				   type: "GET",
 				   url: "/testMavenPro/user/json/userById/"+userId+".html",
 				   dataType: "json",
 				   contentType:"application/json;charset=UTF-8",//必须有
 				   success: function(msg){
 					   $("#idInput").val(msg.id);
 					   $("#userNameInput").val(msg.userName);
 					   
 					  var images = msg.images;
					   if(images.length>0){
						   $("#update_imageList").html("");
						   for(var i=0;i<images.length;i++){
							   var image_Name = images[i].imageName;
							   var image_Url = images[i].imageUrl;
							   var html_str = '<image onclick="showImageDetail(\''+image_Url+'\',\''+image_Name+'\')" style="margin:5px" class="img-circle" width="50px" height="50px" src="'+image_Url+'" alt="'+image_Name+'"/>';
							   $("#update_imageList").append(html_str);
						   }
					   }
 				   }
    			});
    		});
    		//取消模态框
    		$("#cancelUpdateBtn").click(function(){
    			$('#updateUserModal').modal('hide');
    		});
    		$("#update_imageDeleteBtn").click(function(){
    			var userId = $("#idInput").val();
    			var imageUrl = $("#update_imageDetail").attr("src");
    			var imageName = $("#update_imageName").html();
    			var postData = {
   					"imageUrl":"imageUrl",
   					"imageName":"imageName",
   					"userId":"userId"
   				};
    			$.ajax({
  				   type: "POST",
  				   url: "/testMavenPro/user/json/deleteUserImage.html",
  				   dataType: "json",
				   contentType:"application/json;charset=UTF-8",//必须有
  				   data:postData,
  				   success: function(msg){
  					   var flag = msg.flag;
  					   var message = msg.message;
  					   if(flag){
  						   $("#update_imageDetail").attr("src","");
  						   $("#update_imageDetail").attr("alt","");
  						   $("#update_imageName").html("");
  						   $("#update_imageDeleteBtn").addClass("hide");
  						   $("img[src='"+imageUrl+"']").attr("src");
  					   }else{
  						   
  					   }
  				   }
     			});
    		});
    	});
    </script>  
</body>
</html>