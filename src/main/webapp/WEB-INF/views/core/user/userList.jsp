<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/dialog.jsp" %>
	<script type="text/javascript">
		function page(n){
			$("#pageNo").val(n);
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}${baseMapper}">用户列表</a></li>
		<li><a href="${ctx}${baseMapper}/update/0">用户添加</a></li>
	</ul>
	<form id="searchForm" action="${ctx}${baseMapper}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.number+1}"/>
		<input id="orderDir" name="orderDir" type="hidden" value="${pageRequest.orderDir}"/>
		<input id="orderBy" name="orderBy" type="hidden" value="${pageRequest.orderBy}"/>
		<div>
			<label>登录名：</label><input type="text" name="search_EQ_loginName" class="input-small" value="${param.search_EQ_loginName}">
            <label>姓&nbsp;&nbsp;&nbsp;名：</label><input type="text" name="search_LIKE_name" class="input-small" value="${param.search_LIKE_name}">
            &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		</div>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th>#</th>
            <th>登录名</th>
            <th>姓名</th>
            <th>注册日期</th>
            <th>所属角色</th>
            <th>操作</th>
        </tr>
        </thead>
		<tbody>
        <c:forEach items="${page.content}" var="user" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>${user.loginName}&nbsp;</td>
                <td>${user.name}&nbsp;</td>
                <td></td>
                <td></td>
                <td><a href="${ctx}${baseMapper}/update/${user.id}">详情</a>
                    <a href="${ctx}${baseMapper}/delete/${user.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)" >删除</a>
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
    
<a href="${ctx }/weixin/product/productTypeList">product types</a><br>
<a href="${ctx }/weixin/index">index</a><br>
</body>
</html>