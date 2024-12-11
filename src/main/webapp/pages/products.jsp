<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Products</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<%@include file="components/header.jsp" %>
<div class="flex gap-7">
    <nav class="bg-white h-screen relative top-0 left-0 min-w-[250px] font-[sans-serif] overflow-auto">
        <div class="flex flex-col h-full">
            <ul class="space-y-3 my-8 flex-1">
                <c:forEach items="${requestScope.categories}" var="category">
                    <li>
                        <form class="text-sm flex items-center border-r-[5px] border-[#077bff] bg-gray-100 px-8 py-4 transition-all"
                              action="${pageContext.request.contextPath}/products" method="post">
                            <input type="hidden" name="command" value="filter_product_action"/>
                            <input type="hidden" value=${category.getId()}  name="categoryId" />
                            <button type="submit">${category.getName()}</button>
                        </form>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </nav>
    <div>
        <form class="my-3 flex items-center gap-2" action="${pageContext.request.contextPath}/products" method="post">
            <input type="hidden" name="command" value="search_product_action">
            <div class="font-[sans-serif] p-4 w-4/5">
                <div
                        class="w-full bg-gray-100 my-3 flex items-center border max-md:order-1 border-transparent focus-within:border-black focus-within:bg-transparent px-4 rounded-sm h-10 transition-all">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 192.904 192.904"
                         class="fill-gray-400 mr-4 w-4 h-4">
                        <path
                                d="m190.707 180.101-47.078-47.077c11.702-14.072 18.752-32.142 18.752-51.831C162.381 36.423 125.959 0 81.191 0 36.422 0 0 36.423 0 81.193c0 44.767 36.422 81.187 81.191 81.187 19.688 0 37.759-7.049 51.831-18.751l47.079 47.078a7.474 7.474 0 0 0 5.303 2.197 7.498 7.498 0 0 0 5.303-12.803zM15 81.193C15 44.694 44.693 15 81.191 15c36.497 0 66.189 29.694 66.189 66.193 0 36.496-29.692 66.187-66.189 66.187C44.693 147.38 15 117.689 15 81.193z">
                        </path>
                    </svg>
                    <input name="query" type='text' placeholder='Search...'
                           class="w-full outline-none bg-transparent text-black text-sm"/>
                </div>
            </div>
            <button type="submit"
                    class='my-2 px-4 py-2 text-sm rounded-sm font-bold text-white border-2 border-[#007bff] bg-[#007bff] transition-all ease-in-out duration-300 hover:bg-transparent hover:text-[#007bff]'>
                Search
            </button>
        </form>
        <h2 class="text-4xl font-extrabold text-gray-800 mb-12">Products</h2>
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
            <c:forEach items="${requestScope.products}" var="product">
                <div class="bg-white border overflow-hidden rounded-2xl cursor-pointer hover:border-blue-600
                    transition-all relative">
                    <div class="bg-gray-50 p-4 h-[250px] overflow-hidden mx-auto aspect-w-16 aspect-h-8 rounded-b-2xl">
                        <img src="https://readymadeui.com/images/sunglass1.webp" alt="sunglass1"
                             class="h-full w-full object-contain"/>
                    </div>
                    <div class="p-6">
                        <h3 class="text-lg font-bold text-gray-800"> ${product.getName()} </h3>
                        <div class="flex items-center justify-between mt-6">
                            <form action="${pageContext.request.contextPath}/cart" method="post"
                                  class="w-10 h-10  bg-gray-100 flex items-center justify-center rounded-full">
                                <input type="hidden" name="command" value="add_product_cart">
                                <input type="hidden" name="productId" value=${product.getId()}>
                                <button type="submit">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="20px" height="20px"
                                         viewBox="0 0 512 512">
                                        <path
                                                d="M164.96 300.004h.024c.02 0 .04-.004.059-.004H437a15.003 15.003 0 0 0 14.422-10.879l60-210a15.003 15.003 0 0 0-2.445-13.152A15.006 15.006 0 0 0 497 60H130.367l-10.722-48.254A15.003 15.003 0 0 0 105 0H15C6.715 0 0 6.715 0 15s6.715 15 15 15h77.969c1.898 8.55 51.312 230.918 54.156 243.71C131.184 280.64 120 296.536 120 315c0 24.812 20.188 45 45 45h272c8.285 0 15-6.715 15-15s-6.715-15-15-15H165c-8.27 0-15-6.73-15-15 0-8.258 6.707-14.977 14.96-14.996zM477.114 90l-51.43 180H177.032l-40-180zM150 405c0 24.813 20.188 45 45 45s45-20.188 45-45-20.188-45-45-45-45 20.188-45 45zm45-15c8.27 0 15 6.73 15 15s-6.73 15-15 15-15-6.73-15-15 6.73-15 15-15zm167 15c0 24.813 20.188 45 45 45s45-20.188 45-45-20.188-45-45-45-45 20.188-45 45zm45-15c8.27 0 15 6.73 15 15s-6.73 15-15 15-15-6.73-15-15 6.73-15 15-15zm0 0"
                                                data-original="#000000"></path>
                                    </svg>
                                </button>

                            </form>
                            <h4 class="text-lg text-gray-800 font-bold">$${product.getPrice()}</h4>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<%@include file="components/footer.jsp" %>
</body>
</html>
