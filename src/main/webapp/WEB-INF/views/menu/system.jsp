<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<html>
<head>
    <title>菜单导航</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $(".accordion-heading a").click(function () {
                $('.accordion-toggle i').removeClass('glyphicon-chevron-down');
                $('.accordion-toggle i').addClass('glyphicon-chevron-right');
                if (!$($(this).attr('href')).hasClass('in')) {
                    $(this).children('i').removeClass('glyphicon-chevron-right');
                    $(this).children('i').addClass('glyphicon-chevron-down');
                }
            });
            $(".accordion-body a").click(function () {
                $("#menu li").removeClass("active");
                $("#menu li i").removeClass("glyphicon-white");
                $(this).parent().addClass("active");
                $(this).children("i").addClass("glyphicon-white");
            });
            $(".accordion-body a:first i").click();
        });
    </script>
</head>
<body>
<div class="accordion" id="menu">
    <div class="accordion-group">
        <div class="accordion-heading">
            <a class="accordion-toggle" data-toggle="collapse" data-parent="#menu" href="#collapse1"><i
                    class="glyphicon glyphicon-chevron-down"></i>&nbsp;账号管理</a>
        </div>
        <div id="collapse1" class="accordion-body collapse in">
            <div class="accordion-inner">
                <ul class="nav nav-list">
                    <li><a href="${ctx}/core/user/page" target="mainFrame">
                    <i class="glyphicon glyphicon-heart glyphicon-white"></i>&nbsp;用户管理</a></li>
                    <li><a href="${ctx}/core/role/page" target="mainFrame">
                    <i class="glyphicon glyphicon-heart"></i>&nbsp;角色管理</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
