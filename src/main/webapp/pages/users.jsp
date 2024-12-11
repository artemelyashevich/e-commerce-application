<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Users</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<%@include file="components/header.jsp" %>
<form method="post" action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data">
    <input type="hidden" name="command" value="UPLOAD_USER_IMAGE"/>
    <input type="file" name="file"/>
    <input type="submit" value="Upload"/>
</form>
<%@include file="components/footer.jsp" %>
</body>
</html>
