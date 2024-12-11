<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Create product</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<%@include file="components/header.jsp" %>
<form action="${pageContext.request.contextPath}/create-product" method="post"
      class="my-10 font-[sans-serif] text-[#333] max-w-md mx-auto" enctype="multipart/form-data">
    <input type="hidden" name="command" value="create_product_action"/>
    <h1 class="text-2xl font-bold text-center my-5">Create product</h1>
    <label>
        <p class="p-2">Enter name</p>
        <input type="text" placeholder="Name" name="name"
               class="px-4 py-3 bg-[#f0f1f2] focus:bg-transparent w-full text-sm border outline-[#007bff] rounded transition-all"/>
    </label>
    <label class="my-5">
        <p class="p-2">Enter description</p>
        <textarea
                name="description"
                class="p-3 bg-[#f0f1f2] focus:bg-transparent w-full text-sm border outline-[#007bff] rounded transition-all">
        </textarea>
    </label>
    <label class="my-5">
        <p class="p-2">Enter price</p>
        <input type="number" name="price" placeholder="Enter price"/>
    </label>
    <label class="relative font-[sans-serif] w-max">
        <p class="p-2">Choose a category</p>
        <select name="categoryId" id="pet-select"
                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5">
            <option value="">Please choose a category</option>
            <c:forEach items="${requestScope.categories}" var="category">
                <option value=${category.getId()}>${category.getName()}</option>
            </c:forEach>
        </select>
    </label>
    <input type="file" name="file"/>
    <button type="submit"
            class="px-6 py-2.5 w-full !mt-8 text-sm bg-[#007bff] hover:bg-blue-600 text-white rounded active:bg-[#006bff]">
        Create
    </button>
</form>

</body>
</html>
