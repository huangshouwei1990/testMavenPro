<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>用户详情</title>  
</head>  
<body>
	姓名： ${userEntity.userName}<br/>
	密码：${userEntity.password}<br/>
	创建时间: ${userEntity.createTime }<br/>
	最后修改时间：${userEntity.lastUpdateTime }
</body>  
</html>