<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="resource/js/jquery.min.js"></script>
	<script type="text/javascript">
	$(function(){
		$("#jtypese").change(function(){
			 var v = $("#jtypese").val();
			 if(v=='2'){
				 var uname = $("#usernameinput").val();
				 var url = "<%=basePath%>checkcode?username="+uname;
				 $.get(url,function(data,status){
					    alert("验证码: " + data + "\n发送状态: " + status);
					  });
			 }
		});
	});
	</script>

  </head>
  
  <body>
    This is my login page. <br>
    <form action="j_spring_security_check" method="post">
    <table>
    <tr><td>用户名:</td><td><input name="j_username" value="u1" id="usernameinput"></td></tr>
    <tr><td>密码/验证码:</td><td><input name="j_password" value="p1"></td></tr>
    <tr><td>登录方式: </td><td><select name="j_type" id="jtypese"><option value="1">密码</option><option value="2">验证码</option></select></td></tr>
    <tr><td colspan="2"> <input type="submit" value="submit"></td></tr>
    </table>
    </form>
    
   异常： ${SPRING_SECURITY_LAST_EXCEPTION }<br>
  失败次数：  ${SPRING_SESSION_FAIL_TIMES }
  </body>
</html>
