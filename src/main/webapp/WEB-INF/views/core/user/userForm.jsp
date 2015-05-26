<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<html>
<head>
    <title>用户管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#inputForm").validate({
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                }
            });
        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}${baseMapper}page">用户列表</a></li>
    <li class="active"><a href="#">用户${!empty entity.id ? '修改':'添加'}</a></li>
</ul>
<br/>

<form id="inputForm" action="${ctx}${baseMapper}save" method="post" class="form-horizontal">
    <input type="hidden" name="id" value="${entity.id}"/>
    <tags:message content="${errorMsg}"/>
    <div class="control-group">
        <label class="control-label">登录名:</label>
        <div class="controls">
            <input type="text" maxlength="50" class="required" id="loginName"
                   name="loginName" value="${entity.loginName}" 
                   ${empty entity.loginName ?'':'readonly="readonly"'}" placeholder="请填写数字或者字母"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">姓名:</label>

        <div class="controls">
            <input type="text" maxlength="50" class="required" id="name" name="name"
                   value="${entity.name}"  placeholder="请填写中文"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">密码:</label>

        <div class="controls">
            <input type="password" maxlength="50" class="${user.id == 0 ?'required':''}" id="tempPassword"
                   name="tempPassword" value="${entity.tempPassword}">
            <c:if test="${!empty entity.id }"><span class="help-inline">若不修改密码，请留空。</span></c:if>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">用户角色:</label>

        <div class="controls">
            <c:forEach items="${allRoles}" var="role">
                <label class="checkbox inline">
                <input type="checkbox" class="required" value="${role.id }" name="roles"
                <c:if test="${fn:contains(entity.roleIds,role.id)}"> checked </c:if>>
                        ${role.roleLabel }
                </label>
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