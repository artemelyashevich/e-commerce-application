<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><html>
<head>
    <script src="https://cdn.tailwindcss.com"></script>
    <title>Category</title>
</head>
<body>
<%@include file="components/header.jsp" %>
<p>${requestScope.category.getName()}</p>
</body>
</html>
