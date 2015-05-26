<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="个人中心"  var ="pageTitle"></c:set>
<c:set value="systemCenter"  var ="pageFootTab"></c:set>
<%@include file="/WEB-INF/views/weixin/include/header.jsp" %>
<style>
a:hover{text-decoration: none; }
</style>
<div class="myBodyDiv" >
  <shiro:hasPermission name="weixinuser:view">
   	<div class="form-group">
   		<a href="${ctx }/weixin/user/weixinUserList" class="btn-default form-control" style="position: relative;height: 40px;">
   		<label style="position:absolute ;left: 20px;font-size: medium; color:#08c ;"><b>系统用户</b></label>
   		<label style="position:absolute ;right: 0px;">》</label>
   		</a> 
   	</div>
  </shiro:hasPermission>
   <shiro:hasPermission name="importuser:edit">
   	<div class="form-group">
   		<a href="${ctx }/weixin/user/improtUser" class="btn-default form-control" style="position: relative;height: 40px;">
   		<label style="position:absolute ;left: 20px;font-size: medium; color:#08c ;"><b>导入微信用户</b></label>
   		<label style="position:absolute ;right: 0px;">》</label>
   		</a> 
   	</div>
   </shiro:hasPermission>
   <shiro:hasPermission name="wusergroup:edit">
   	<div class="form-group">
   		<a href="${ctx }/weixin/user/userGroup" class="btn-default form-control" style="position: relative;height: 40px;">
   		<label style="position:absolute ;left: 20px;font-size: medium; color:#08c ;"><b>分组列表</b></label>
   		<label style="position:absolute ;right: 0px;">》</label>
   		</a> 
   	</div>
   </shiro:hasPermission>
   <shiro:hasPermission name="wusergroup:edit">
   	<div class="form-group">
   		<a href="${ctx }/weixin/user/usergroupList" class="btn-default form-control" style="position: relative;height: 40px;">
   		<label style="position:absolute ;left: 20px;font-size: medium; color:#08c ;"><b>用户分组</b></label>
   		<label style="position:absolute ;right: 0px;">》</label>
   		</a> 
   	</div>
   </shiro:hasPermission>
   <shiro:hasPermission name="prodtype:edit">
   	<div class="form-group">
   		<a href="${ctx }/weixin/product/productTypeList" class="btn-default form-control" style="position: relative;height: 40px;">
   		<label style="position:absolute ;left: 20px;font-size: medium; color:#08c ;"><b>产品类型</b></label>
   		<label style="position:absolute ;right: 0px;">》</label>
   		</a> 
   	</div>
   </shiro:hasPermission>
   <shiro:hasPermission name="shopauthc:view">
   	<div class="form-group">
   		<a href="${ctx }/weixin/shops/passShopList" class="btn-default form-control" style="position: relative;height: 40px;">
   		<label style="position:absolute ;left: 20px;font-size: medium; color:#08c ;"><b>店铺列表</b></label>
   		<label style="position:absolute ;right: 0px;">》</label>
   		</a> 
   	</div>
   </shiro:hasPermission>
   <shiro:hasPermission name="shopauthc:edit">
   	<div class="form-group">
   		<a href="${ctx }/weixin/shops/verifyShopList" class="btn-default form-control" style="position: relative;height: 40px;">
   		<label style="position:absolute ;left: 20px;font-size: medium; color:#08c ;"><b>待审核店铺</b></label>
   		<label style="position:absolute ;right: 0px;">
   		<c:if test="${authcShops gt 0}"><span class="badge" style="color:black ;background-color: red;">${authcShops }</span></c:if>》</label>
   		</a> 
   	</div>
   </shiro:hasPermission>
   	<div style="margin-bottom: 50px;"></div>
</div>
<%@include file="/WEB-INF/views/weixin/include/footer.jsp" %>
    