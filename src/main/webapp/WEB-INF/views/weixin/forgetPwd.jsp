<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<html>
<head>
    <title>找回密码</title>
    <meta name="decorator" content="default"/>
   	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link href="${ctxStatic }/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctxStatic }/mobileSys.css" rel="stylesheet">
    <script src="${ctxStatic }/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="${ctxStatic }/bootstrap/3.3.2/js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>
    <div class="myBodyDiv" >
    	<form action="${ctx }/weinxin/forgetPwd" method="post">
    	<div class="form-group"><div class="input-group">
    		<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
    		<input name="loginName" class="required form-control" id="longname" maxlength="11" placeholder="请输入手机号码"/>
    	</div></div>
    	<div class="form-group"><div class="input-group">
    		<span class="input-group-addon"><span class="glyphicon glyphicon-check"></span></span>
    		<input class="required form-control" id="checkcode" name="code" maxlength="4" placeholder="请输入验证码" type="text"/>
    		<span class="input-group-addon btn-primary" onclick="startTimer()">获取验证码 <span class="badge" id="codeTimer">0</span></span>
    	</div></div>
    	<div class="form-group"><div class="input-group">
    		<span class="input-group-addon"><span class="glyphicon glyphicon-eye-open"></span></span>
    		<input name="tmpPassword" id="pwd1" class="required form-control" maxlength="20" placeholder="请输入新密码" type="password"/>
    	</div></div>
    	<div class="form-group">
    		<input class="btn btn-primary  form-control" onclick="return checkPwd();" type="submit" value="找&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;回"/>
    	</div>
    	</form>
    </div>
    <script type="text/javascript">
    	var timer ;
    	function startTimer(){
    		var time = $("#codeTimer").text();
       		var telnum = $("#longname").val();
       		if(time==0){
       		  if(/^\d{11}$/.test(telnum)){
       			$.ajax({url:'${ctx}/core/sms/sendCode',type:'post',
       				data:{'telnum':telnum},success:function(data){
    	   			if('true'==data){
    			   		$("#codeTimer").text(600);
    			   		timer = setInterval(function(){
    			   			var count = parseInt($("#codeTimer").text());
    			   			if(count==0){
    			   				 clearInterval(timer);
    			   			}else
    			   	        	$("#codeTimer").text(count-1);
    				   	},1000);
    	   				
    	   			}else
       					alert(data);
       			}});
       		  }else{
       			  alert("手机号码不对");
       		  }
       		}
    	}
    	function checkPwd(){
    		if($.trim($("#longname").val())==''){
    			alert("请输入手机号码");
    			$("#longname").focus();
    			return false;
    		}
    		if($.trim($("#checkcode").val())==''){
    			alert("请输入验证码");
    			$("#checkcode").focus();
    			return false;
    		}	
    		var pwd1 = $("#pwd1").val();
    		if(pwd1.length<6){
    			alert("密码最低6位数");
    			$("#pwd1").focus();
    			return false;
    		}
    		return ture;
    	}
    </script>
</body>
</html>
    