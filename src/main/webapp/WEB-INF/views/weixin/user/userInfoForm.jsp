<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="个人信息"  var ="pageTitle"></c:set>
<c:set value="custmerCenter"  var ="pageFootTab"></c:set>
<%@include file="/WEB-INF/views/weixin/include/header.jsp" %>
   <div class="myBodyDiv" >
    <form action="${ctx }${baseMapper }saveuserinfo" method="post" >
    	<input name="id" value="${entity.id }" type="hidden"/>
    	<div class="form-group"><div class="input-group">
    		<span class="input-group-addon" >账号</span>
  			<input type="text" class="form-control"  readonly="readonly"  value="${entity.loginName }"/>
    	</div></div>
    	<div class="form-group"><div class="input-group">
    		<span class="input-group-addon" >名称</span>
  				<input name="name" maxlength="120" class="form-control" placeholder="用户名称" value="${entity.name }"/>
    		</div>
    	</div>
    	<div class="form-group"><div class="input-group">
    		<span class="input-group-addon" >电话</span>
  				<input name="telphone" maxlength="20" class="form-control" placeholder="联系电话" value="${entity.telphone }"/>
    		</div>
    	</div>
    	<div class="form-group"><div class="input-group">
   			<span class="input-group-addon" >邮箱</span>
			<input name="email" class="form-control" placeholder="邮箱" value="${entity.email }"/>
   		</div></div>
    	<div class="form-group"><div class="input-group">
    			<span class="input-group-addon">地址</span>
  				<input type="text"  name="address" class="form-control" placeholder="输入地址" value="${entity.address }"/>
    		</div>
    	</div>
    	<div class="form-group">
		<span style="color:red;">${errorMsg }</span>
		   <input id="btnSubmit" class="btn btn-primary form-control" type="submit" value="保 存"/>&nbsp;
		</div>
		<label id="errorMsg" style="color: red;margin-bottom: 42px;"></label>
    	</form>
    </div>
</div>
<%@include file="/WEB-INF/views/weixin/include/footer.jsp" %>
    