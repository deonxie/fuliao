<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="个人中心"  var ="pageTitle"></c:set>
<c:set value="custmerCenter"  var ="pageFootTab"></c:set>
<%@include file="/WEB-INF/views/weixin/include/header.jsp" %>
<style>
/* a > span{padding-bottom: 15px;} */
</style>
<div class="myBodyDiv" >
    <table class="table table-striped table-bordered table-condensed">
    	<c:forEach items="${page.content }" var="favt" varStatus="index">
    	<tr>
    	<c:if test="${empty favt.shop }">
    		<td width="50px"><img src="${ctx }${favt.prodcut.coverImg.relativePath}" style="width: 50px;height: 50px;"></td>
    		<td><a href="${ctx }/weixin/product/productDetail?id=${favt.prodcut.id}">${favt.prodcut.name }</a></td>
    		<td>产品</td>
    	</c:if><c:if test="${empty favt.prodcut }">
    		<td width="50px"><img src="${ctx }${favt.shop.logo.relativePath}" style="width: 50px;height: 50px;"></td>
    		<td><a href="${ctx }/weixin/shops/shopDetail?id=${favt.shop.id}">${favt.shop.name }</a></td>
    		<td>店铺</td>
    	</c:if>
    		<td><a href="${ctx }/weixin/user/deleteFavt?id=${favt.id}">删除</a></td>
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
<form id="pageForm" action="${ctx }/weixin/user/myFavtprod" method="post">
 <input id="pageNo" name="pageNo" value="${pageUtil.pageNo }" type="hidden"/>
</form>
    <script type="text/javascript">
    function page(n){
		$("#pageNo").val(n);
		$("#pageForm").submit();
    	return false;
    }
    </script>
</div>
<%@include file="/WEB-INF/views/weixin/include/footer.jsp" %>
    