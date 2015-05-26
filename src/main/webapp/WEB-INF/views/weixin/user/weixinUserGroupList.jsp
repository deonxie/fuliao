<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="用户分组"  var ="pageTitle"></c:set>
<c:set value="systemCenter"  var ="pageFootTab"></c:set>
<%@include file="/WEB-INF/views/weixin/include/header.jsp" %>
<style>
/* a > span{padding-bottom: 15px;} */
</style>
<div class="myBodyDiv" >
  <form action="${ctx }/weixin/user/usergroupList" method="post" id="searchForm">
	    <div class="form-group" style="margin-bottom: 1px;"><div class="input-group">
		    <input class="form-control" name="search_LIKE_nickName" />
		    <span class="input-group-addon" onclick="$('#searchForm').submit();"><span class="glyphicon glyphicon-search"></span>搜索</span>
	    </div></div>
    </form>
    <table class="table table-striped table-bordered">
    	<c:forEach items="${page.content }" var="weiUser" varStatus="index">
    	<tr>
    		<td><img alt="头像" src="${weiUser.headimgurl }" style="width: 50px;height: 50px;"></td>
    		<td>${weiUser.nickName }</td>
    		<td><c:if test="${empty weiUser.userGroup.name}">
    		<label onclick="updategroup('${weiUser.id}',0)">未分组</label>
    		</c:if><c:if test="${!empty weiUser.userGroup.name}">
    		<label onclick="updategroup('${weiUser.id}',${weiUser.userGroup.id })">${weiUser.userGroup.name}</label>
    		</c:if>
    		</td>
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
    <form id="pageForm" action="${ctx }/weixin/user/usergroupList" method="post">
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
<div class="modal" id="updateGroup" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      	<h4>分组</h4>
      </div>
      <div class="modal-body">
        <form action="${ctx }/weixin/user/saveUsergroup" method="post">
          <input type="hidden" class="form-control" id="userIds" name="userid">
          <div class="input-group">
            <span class="input-group-addon">分组:</span>
           	<select id="groupids" name="groupid" class="form-control"><c:forEach items="${allgroups }" var="grop">
           		<option value="${grop.id }">${ grop.name}</option>
           	</c:forEach>
           	</select>
          </div><br>
        <button type="submit" class="btn btn-primary form-control">保存</button>
        </form>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
	function updategroup(userid,groupid){
		$("#userIds").val(userid);
		$("#groupids option[value="+groupid+"]").attr("selected",true);
		$('#updateGroup').modal('show');
	}
</script>
<%@include file="/WEB-INF/views/weixin/include/footer.jsp" %>
    