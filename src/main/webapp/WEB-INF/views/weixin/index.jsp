<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="中大代购平台"  var ="pageTitle"></c:set>
<c:set value="mainSystems"  var ="pageFootTab"></c:set>
<%@include file="/WEB-INF/views/weixin/include/header.jsp" %>
<script src="${ctxStatic }/jweixin-1.0.0.js" type="text/javascript"></script>

<div class="myBodyDiv" style="padding-bottom: 52px;border: 0px;">
    	<img alt="" src="${ctxStatic }/images/logo_zd.png" width="100%"> 
    	<h3>欢迎使用中大辅料代购平台</h3>
    	<ul style="text-align: left;">
    		<li>1 回复公众账号：重置密码：手机号码，系统会将密码重置为000000</li>
    		<li>2 当用户发布求购时，系统会将求购信息推送到48小时内与公众号联系，且求购类型在商家主营业务内的商家。</li>
    	</ul>
    	
</div>
<%@include file="/WEB-INF/views/weixin/include/footer.jsp" %>
