<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Admin product</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>

<%@include file="components/header.jsp" %>
<div class="font-sans my-10 flex items-center justify-center">
    <div class="grid items-start grid-cols-1 lg:grid-cols-3">
        <div class="col-span-2 grid grid-cols-2 lg:sticky top-0">

            <img src="https://readymadeui.com/images/product6.webp" alt="Product"
                 class="w-full h-full object-cover object-top shadow-md"/>
        </div>

        <div class="py-6 px-8 max-lg:max-w-2xl">
            <div>
                <h2 class="text-2xl font-extrabold text-gray-800">${requestScope.product.getName()}</h2>
                <p class="text-sm text-gray-400 mt-2">${requestScope.category.getName()}</p>
            </div>

            <div class="mt-8">
                <p class="text-gray-800 text-4xl font-bold">$${requestScope.product.getPrice()}</p>
            </div>

            <form action="${pageContext.request.contextPath}/product" method="post" class="mt-8 space-y-4">
                <input type="hidden" name="command" value="delete_product_action">
                <input type="hidden" name="productId" value=${requestScope.product.getId()}>
                <button type="submit"
                        class="w-full px-4 py-2.5 border border-gray-800 bg-transparent hover:bg-gray-50 text-gray-800 text-sm font-semibold rounded-md">
                    Delete
                </button>
            </form>

            <div class="mt-8">
                <div>
                    <h3 class="text-xl font-bold text-gray-800">Product Description</h3>
                    <p class="text-sm text-gray-800 mt-4">${requestScope.product.getDescription()}</p>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="components/footer.jsp" %>
</body>
</html>
