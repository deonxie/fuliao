<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="产品类型列表"  var ="pageTitle"></c:set>
<c:set value="systemCenter"  var ="pageFootTab"></c:set>
<%@include file="/WEB-INF/views/weixin/include/header.jsp" %>
<div class="myBodyDiv">
    <form action="${ctx }/weixin/product/productTypeList" method="post" id="searchForm">
	    <div class="form-group"><div class="input-group">
	    	<span class="input-group-addon" onclick="saveTypes(0,'')"><span class="glyphicon glyphicon-plus"></span>添加</span>
		    <input class="form-control" name="search_LIKE_typeName" placeholder="输入关键字" value="${params_value.LIKE_typeName }" />
		    <span class="input-group-addon" onclick="$('#searchForm').submit();"><span class="glyphicon glyphicon-search"></span>搜索</span>
	    </div></div>
    </form>
	<table class="table table-striped table-bordered table-condensed">
		<tbody>
        <c:forEach items="${page.content}" var="type" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>${type.typeName}</td>
                <td><fmt:formatDate value="${type.createTime}" pattern="yyyy-MM-dd"/> </td>
                <td><span onclick="saveTypes(${type.id}, '${type.typeName}')" >修改</span></td>
            </tr>
        </c:forEach>
		</tbody>
	</table>
<c:if test="${pageUtil.pageNo gt 1 }">
   <span class="glyphicon glyphicon-chevron-left" style="width: 35px;height: 35px;font-size: 35px;color: #08c;
   position: fixed; top: 50%;z-index: 5;display: inline-block;left: 0px;" onclick="page(${pageUtil.pageNo-1})"></span>
</c:if>

<c:if test="${pageUtil.pageNo lt page.totalPages}">
   <span class="glyphicon glyphicon-chevron-right" style="width: 35px;height: 35px;font-size: 35px;color: #08c;
   position: fixed; top: 50%;z-index: 5;display: inline-block;right: 0px;" onclick="page(${pageUtil.pageNo+1})"></span>
</c:if>
    
</div>
<form id="pageForm" action="${ctx }/weixin/user/userGroup" method="post">
 <input id="pageNo" name="pageNo" value="${pageUtil.pageNo }" type="hidden"/>
</form>
<script type="text/javascript">
	function page(n){
		$("#pageNo").val(n);
		$("#pageForm").submit();
    	return false;
    }
	function saveTypes(id,name){
		$("#typeId").val(id);
		$("#typeName").val(name);
		$("#myModal").modal('show');
	}
</script>
    <div class="modal" id="myModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	        <span aria-hidden="true">&times;</span></button>
	        <span class="modal-title">产品类型</span>
	      </div>
	      <div class="modal-body">
	     <form action="${ctx }${baseMapper }saveType" method="post" class="form-horizontal">
	     	<input name="id" type="hidden" id="typeId" >
        	<div class="form-group"><div class="input-group">
        		<span class="input-group-addon">类型</span>
        		<input name="typeName" class="required form-control" maxlength="100" id="typeName" >
        	</div></div>
	        <button type="submit" class="btn btn-primary btn-xs form-control">保存</button>
	     </form>
	      </div>
	    </div>
	  </div>
	</div>
<%@include file="/WEB-INF/views/weixin/include/footer.jsp" %>