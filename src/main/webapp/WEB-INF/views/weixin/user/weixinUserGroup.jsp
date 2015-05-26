<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="分组列表"  var ="pageTitle"></c:set>
<c:set value="systemCenter"  var ="pageFootTab"></c:set>
<%@include file="/WEB-INF/views/weixin/include/header.jsp" %>
<style>
/* a > span{padding-bottom: 15px;} */
</style>
<div class="myBodyDiv" >
  <form action="${ctx }/weixin/user/userGroup" method="post" id="searchForm">
	    <div class="form-group" style="margin-bottom: 1px;"><div class="input-group">
	    	<span class="input-group-addon" onclick="$('#addGroup').modal('show');"><span class="glyphicon glyphicon-plus"></span>添加</span>
		    <input class="form-control" name="search_LIKE_name" />
		    <span class="input-group-addon" onclick="$('#searchForm').submit();"><span class="glyphicon glyphicon-search"></span>搜索</span>
	    </div></div>
    </form>
    <font color="red"> ${errorMsg }</font>
    <table class="table table-striped table-bordered">
    	<c:forEach items="${page.content }" var="group" varStatus="index">
    	<tr>
    		<td>${index.count }</td>
    		<td>${group.name }</td>
    		<td><span onclick="updateGroup(${group.id},'${group.name}')">修改</span></td>
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
    <form id="pageForm" action="${ctx }/weixin/user/userGroup" method="post">
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
<!-- modle -->
<div class="modal" id="addGroup" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      	<h4>添加</h4>
      </div>
      <div class="modal-body">
        <form action="${ctx }/weixin/user/saveGroup" method="post">
          <div class="input-group">
            <span class="input-group-addon">名称:</span>
            <input type="text" class="form-control" name="name">
          </div><br>
        	<button type="submit" class="btn btn-primary form-control">保存</button>
        </form>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
	function updateGroup(id,name){
		$("#oldId").val(id);
		$("#oldName").val(name);
		$('#updateGroup').modal('show');
	}
</script>
<div class="modal" id="updateGroup" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      	<h4>修改</h4>
      </div>
      <div class="modal-body">
        <form action="${ctx }/weixin/user/updateGroup" method="post">
          <input type="hidden" id="oldId" name="id" />
          <div class="input-group">
            <span class="input-group-addon">名称:</span>
            <input type="text" id="oldName" class="form-control" name="name">
          </div><br>
        	<button type="submit" class="btn btn-primary form-control">保存</button>
        </form>
      </div>
    </div>
  </div>
</div>
<%@include file="/WEB-INF/views/weixin/include/footer.jsp" %>
    