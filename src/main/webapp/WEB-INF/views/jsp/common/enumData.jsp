<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

레벨1
<c:out value="${arrTitle1}" /><br/>
<c:out value="${arrTitleDetail1}" /><br/>
----------------------------------------------<br/>
레벨2
<c:out value="${arrTitle2}" /><br/>
<c:out value="${arrTitleDetail2}" /><br/>
----------------------------------------------<br/>
레벨3[지정값 없음]
<c:out value="${levelInfo3}" /><br/>
levelValue :<c:out value="${levelValue}" /><br/>


</body>
</html>
