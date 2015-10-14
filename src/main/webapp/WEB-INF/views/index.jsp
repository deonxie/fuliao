<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<html>
<head>
    <title>管理后台</title>
    <%@include file="/WEB-INF/views/include/dialog.jsp" %>
    <meta name="decorator" content="default"/>
    <style type="text/css">
        .brand {font-family:Helvetica, Georgia, Arial, sans-serif;font-size:26px;padding-left:33px;}
        #footer {margin:8px 0 0 0;padding:3px 0 0 0;font-size:11px;text-align:center;border-top:2px solid #0663A2;}
        #footer, #footer a {color:#999;} 
    </style>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#menu li").click(function(){
                $("#menu li").removeClass("active");
                $(this).addClass("active");
                if(!$("#openClose").hasClass("close")){
                    $("#openClose").click();
                }
            });
        });
    </script>
</head>
<body>
<div id="main">
    <nav id="header" class="navbar navbar-default">
  		<div class="container-fluid">
  			<div class="navbar-header"><span class="navbar-brand brand">管理后台</span></div>
  			<div class="collapse navbar-collapse" >
  				<ul id="menu" class="nav navbar-nav" style="margin-bottom: 0px;">
  				 	<li class="active"><a class="menu" href="${ctx}/menuList/system" target="menuFrame">系统管理</a></li>
                    <li><a class="menu" href="${ctx}/menuList/system" target="menuFrame">系统监控</a></li>
                    <li><a class="menu" href="${ctx}/menuList/system" target="menuFrame">统计分析</a></li>
  				</ul>
  				<ul class="nav navbar-nav navbar-right" style="margin-bottom: 0px;">
  				<li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#" title="个人信息">您好, </a>
                        <ul class="dropdown-menu">
                            <li><a href="" target="mainFrame"><i class="glyphicon glyphicon-user"></i>&nbsp; 个人信息</a></li>
                            <li><a href="" target="mainFrame"><i class="glyphicon glyphicon-lock"></i>&nbsp;  修改密码</a></li>
                        </ul>
                    </li>
                    <li><a href="${ctx}/logout" title="退出登录"><i class="glyphicon glyphicon-log-out"></i>退出</a></li>
                    <li>&nbsp;</li>
  				</ul>
  			</div>
    	</div>
    </nav>	
    
    <div class="container-fluid">
        <div id="content" class="container-fluid">
            <div id="left" >
                <iframe id="menuFrame" name="menuFrame" src="${ctx}/menuList/system" style="overflow:visible;"
                        scrolling="yes" frameborder="no" width="100%" height="650"></iframe>
            </div>
            <div id="openClose" class="close">&nbsp;</div>
            <div id="right" >
                <iframe id="mainFrame" name="mainFrame" src="" style="overflow:visible;"
                        scrolling="yes" frameborder="no" width="100%" height="650"></iframe>
            </div>
        </div>
        <div id="footer" class="row-fluid">
        @copy think
        </div>
    </div>
</div>
<script type="text/javascript">
    var leftWidth = "160"; // 左侧窗口大小
    function wSize(){
        var minHeight = 500, minWidth = 980;
        var strs=getWindowSize().toString().split(",");
        $("#menuFrame, #mainFrame, #openClose").height((strs[0]<minHeight?minHeight:strs[0])-$("#header").height()-$("#footer").height()-40);
        $("#openClose").height($("#openClose").height()-5);
        if(strs[1]<minWidth){
            $("#main").css("width",minWidth-10);
            $("html,body").css({"overflow":"auto","overflow-x":"auto","overflow-y":"auto"});
        }else{
            $("#main").css("width","auto");
            $("html,body").css({"overflow":"hidden","overflow-x":"hidden","overflow-y":"hidden"});
        }
        $("#right").width($("#content").width()-$("#left").width()-$("#openClose").width()-5);
    }
</script>
<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>