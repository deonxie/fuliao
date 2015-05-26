<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<html>
<head>
    <title>${pageTitle }</title>
    <meta name="decorator" content="default"/>
   	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link href="${ctxStatic }/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctxStatic }/mobileSys.css" rel="stylesheet">
    <script src="${ctxStatic }/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="${ctxStatic }/bootstrap/3.3.2/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${ctxStatic }/jweixin-1.0.0.js" type="text/javascript"></script>
</head>
<body>
