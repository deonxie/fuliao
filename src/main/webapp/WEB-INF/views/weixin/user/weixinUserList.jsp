<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="微信用户列表"  var ="pageTitle"></c:set>
<c:set value="systemCenter"  var ="pageFootTab"></c:set>
<%@include file="/WEB-INF/views/weixin/include/header.jsp" %>
<style>
/* a > span{padding-bottom: 15px;} */
</style>
<div class="myBodyDiv" >
  <form action="${ctx }/weixin/user/weixinUserList" method="post" id="searchForm">
	    <div class="form-group" style="margin-bottom: 1px;"><div class="input-group">
		    <input class="form-control" name="search_LIKE_nickName" />
		    <span class="input-group-addon" onclick="$('#searchForm').submit();"><span class="glyphicon glyphicon-search"></span>搜索</span>
	    </div></div>
    </form>
    <table class="table table-striped table-bordered">
    	<c:forEach items="${page.content }" var="weiUser" varStatus="index">
    	<tr>
    		<td><img alt="头像" src="${weiUser.headimgurl }" style="width: 50px;height: 50px;"></td>
    		<td>${weiUser.nickName }</td><td>${weiUser.sex eq 1 ?'男':'女' }</td>
    		<td>${weiUser.city }</td>
    	</tr>
    	</c:forEach>
    </table>
    
<c:if test="${pageUtil.pageNo gt 1 }">
   <span class="glyphicon glyphicon-chevron-left" style="width: 35px;height: 35px;font-size: 35px;color: #08c;
   position: fixed; top: 50%;z-index: 5;display: inline-block;left: 0px;" onclick="page(${pageUtil.pageNo-1})"></span>
</c:if>

<c:if test="${pageUtil.pageNo lt page.totalPages}">
   <span class="glyphicon glyphicon-chevron-right" style="width: 35px;height: 35px;font-size: 35px;color: #08c;
   position: fixed; top: 50%;z-index: 5;display: inline-block;right: 0px;" onclick="page(${pageUtil.pageNo+1})"></span>
</c:if>
    <form id="pageForm" action="${ctx }/weixin/user/weixinUserList" method="post">
 <input id="pageNo" name="pageNo" value="${pageUtil.pageNo }" type="hidden"/>
</form>
    <script type="text/javascript">
    function page(n){
		$("#pageNo").val(n);
		$("#pageForm").submit();
    	return false;
    }
    </script>
<div style="height: 50px;"></div>
</div>
<%@include file="/WEB-INF/views/weixin/include/footer.jsp" %>
    