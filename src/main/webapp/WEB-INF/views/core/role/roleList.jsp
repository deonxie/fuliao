<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<html>
<head>
    <title>角色管理</title>
    <meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/dialog.jsp" %>
    <script type="text/javascript">
        function page(n) {
            $("#pageNo").val(n);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}${baseMapper}">角色列表</a></li>
    <li><a href="${ctx}${baseMapper}/update/0">角色添加</a></li>
</ul>
<form id="searchForm" action="${ctx}${baseMapper}page" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${pageUtil.pageNo}"/>
    <div>
        <label>角色名：</label>
        <input type="text" name="search_LIKE_roleLabel" class="input-small" placeholder="角色名"
               value="${params_value.search_LIKE_roleLabel}">
        &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
    </div>
</form>
<tags:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead><tr><th>#</th><th>名称</th><th>操作</th></tr></thead>
    <tbody>
    <c:forEach items="${page.content}" var="role" varStatus="status">
        <tr>
            <td>${status.count}</td>
            <td>${role.roleLabel}&nbsp;</td>
            <td><a href="${ctx}${baseMapper}update/${role.id}">详情</a>
                <a href="${ctx}${baseMapper}delete/${role.id}" onclick="return confirmx('确认要删除该角色吗？', this.href)">删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<nav>
  <ul class="pager">
  	<c:choose><c:when test="${pageUtil.pageNo gt 1}">
  		<li><a href="#" onClick="page(${pageUtil.pageNo-1})">上一页</a></li>
  	</c:when><c:otherwise>
  		<li class="disabled"><a href="#">上一页</a></li>
  	</c:otherwise></c:choose>
    <li class="controls"><strong>总页数${page.totalPages},当前第${pageUtil.pageNo}页</strong></li>
    <c:choose><c:when test="${pageUtil.pageNo lt page.totalPages}">
  		<li><a href="#" onClick="page(${pageUtil.pageNo+1})">下一页</a></li>
  	</c:when><c:otherwise>
  		<li class="disabled"><a href="#">下一页</a></li>
  	</c:otherwise></c:choose>
    </ul>
</nav>
</body>
</html>