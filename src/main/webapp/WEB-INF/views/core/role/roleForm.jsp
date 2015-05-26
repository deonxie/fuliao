<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<html>
<head>
    <title>角色管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#inputForm").validate({
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox"
            });
        });
        
        function selectAll(obj){
        	if(obj.checked){
        		$(obj).parent().parent().parent().find("tbody :checkbox").attr('checked','checked');
        	}
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}${baseMapper}page">角色列表</a></li>
    <li class="active"><a href="#">角色${empty entity.id ? '添加':'修改'}</a></li>
</ul>
<br/>

<form id="inputForm" action="${ctx}${baseMapper}/save" method="post" class="form-horizontal">
    <input type="hidden" name="id" value="${entity.id}"/>
    <tags:message content="${errorMsg}"/>
    <div class="control-group">
        <label class="control-label">角 色:</label>
        <div class="controls">
            <input type="text" maxlength="50" class="required" id="roleName" name="roleName" 
            value="${entity.roleName}" placeholder="请填写数字或者字母" ${empty entity.roleName ?'':'readonly="readonly"' }>
        </div>
    </div>
	<div class="control-group">
        <label class="control-label">角色名:</label>
        <div class="controls">
            <input type="text" maxlength="50" class="required" id="roleLabel"
                   name="roleLabel" value="${entity.roleLabel}" placeholder="请输入角色名称" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">权限信息:</label>
        <div class="controls">
           <c:forEach items="${allTypsPerms }" var="typePerms">
            <table class="table table-bordered">
            	<caption style="color:#08c;text-align: left;">${typePerms.key }
            	<label>&nbsp;&nbsp;<input type="checkbox" onchange="selectAll(this)" />全选</label>
            	</caption>
            	<tbody>
            		<tr><td>
            			<c:forEach items="${typePerms.value }" var="perms">
            				<label><input type="checkbox" value="${perms.value }" name="perms"
            				${fn:contains(entity.permissions,perms.value) ? 'checked="checked"':'' }/>${perms.displayName }&nbsp;&nbsp;</label>
            			</c:forEach>
            		</td></tr>
            	</tbody>
        	</table>
        </c:forEach>
          
        </div>
    </div>

    <div class="form-actions">
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form>
</body>
</html>