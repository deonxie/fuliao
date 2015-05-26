<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<table class="myNavTable">
	<tr>
		<%-- <th class="${pageFootTab eq 'mainSystems' ?'myActive':'' }" onclick="footerLocation('${ctx }/weixin/')">
		<a href="${ctx }/weixin/" style="color:#F2683E;"><span><span class="glyphicon glyphicon-home"></span>首 页</span></a>
		</th> --%>
		<th class="${pageFootTab eq 'mainRequests' ?'myActive':'' }" onclick="footerLocation('${ctx }/weixin/reqproduct/reqProductList')">
		<a href="${ctx }/weixin/reqproduct/reqProductList" style="color:#F2683E;"><span><span class="glyphicon glyphicon-shopping-cart"></span>求 购</span></a>
		</th><th class="${pageFootTab eq 'mainProdcuts' ?'myActive':'' }" onclick="footerLocation('${ctx }/weixin/product/productList')">
		<a href="${ctx }/weixin/product/productList" style="color:#F2683E;"><span><span class="glyphicon glyphicon-th"></span>产 品</span></a>
		</th><th class="${pageFootTab eq 'custmerCenter' ?'myActive':'' }" onclick="footerLocation('${ctx }/weixin/user/userCenter')">
		<a href="${ctx }/weixin/user/userCenter" style="color:#F2683E;"><span><span class=" glyphicon glyphicon-user"></span>个 人</span></a>
		</th>
		<shiro:hasPermission name="weixinsystem:view">
		<th class="${pageFootTab eq 'systemCenter' ?'myActive':'' }" onclick="footerLocation('${ctx }/weixin/system/systemCenter')">
		<a href="${ctx }/weixin/system/systemCenter" style="color:#F2683E;"><span><span class=" glyphicon glyphicon-cog"></span>管 理</span></a>
		</th>
		</shiro:hasPermission>
	</tr>
</table>
<script type="text/javascript">
	function footerLocation(url){
		window.location.href = url;
	}
</script>
</body>
</html>