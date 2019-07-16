<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script type="text/javascript">
	function login() {
		var userName=$("input[name='userName']").val();
		var password=$("input[name='password']").val();
		var pass=$("input[name='confirm_password']").val();
	
		if(password!=pass){
			$('#usererror1').html('please input correctpass ');
		}else{
			$('#usererror1').html('');
		}
		/* $('#content').html('<p>'+userName+','+password+'</p>') */
		if(userName==""){
			$('#usererror').html('please input userName ');
			return false;
		}else{
			$('#usererror').html('');
		}
		//2.发送ajax
		$.get(
				'/uservlet/login',{userName:userName,password:password},
				function (data) {
					$('#content').html('<p>'+data+'</p>')
				}
				);
	}
</script>
</head>
<body>
	<!-- 前端页面给后台的servlet传递参数的方式有3种 
		1st:Method:直接拼接url,不安全，他有长度的限制
		eg:http://localhost:8080/uservlet/login?name1=value1&name2=value2
		2nd:放在表单里传递，所以对于程序员，表单很重要，因为他嗯呢该传递参数
		3th:ajax的方式传递参数
	-->
	<!-- 表单方式 -->
	<!-- <form action="" method="get">
		<input name="uesrName"><br>
		<input name="password" type="password">
		<input value="login" type="submit">
	</form> -->
	
	<!-- ajax方式 -->
	<div>
		<div>
		
			<p>姓名：<input id="userName" name="userName"><span id="usererror" style="color: red"></span></p>
		</div>
		<div>
			<p>密码：<input id="password" name="password" type="password"></p>
		</div>
		<div>
			<p>验证密码：<input id="confirm_password" name="confirm_password" type="password"><span id="usererror1" style="color: red"></span></p>
		</div>
		<div>
			<p>性别：<input name="sex" type="radio">男<input name="sex" type="radio" checked="checked">女</p>
		</div>
		<div>
			<p>邮箱：<input id="email" name="email" type="email"></p>
		</div>
		<div>
			<p>出生年月：<input name="dateime" type="date"></p>
		</div>
		<div>
			<p>
				地址：<select name="select">
						<option>太原</option>
						<option>长治</option>
						<option>晋城</option>
					</select>
			</p>
		</div>
		<div>
			<p>爱好：学习<input name="check" type="checkbox">
			                  上课<input name="check" type="checkbox">
			</p>
		</div>
		<div>
			<p><input type="button" value="login" onclick="login();" ></p>
		</div>
		<div>
			<p><input type="reset"></p>
		</div>
		<div id="content"></div>
	</div>
	
</body>
</html>