<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Categories</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<%@include file="components/header.jsp" %>
<div class="flex flex-col justify-center items-center gap-5">
    <h1 class="mt-3 font-bold text-2xl">Create new Category</h1>
    <form action="${pageContext.request.contextPath}/categories" method="post" class="my-4 font-[sans-serif] w-2/3 mx-auto">
        <input type="hidden" name="command" value="create_category">
        <input type="text" name="name" placeholder="Enter name"
               class="px-4 py-3 bg-gray-100 w-full text-sm outline-none border-2 border-blue-500 rounded"/>
        <button type="submit"
                class="px-7 py-2 mt-2 mx-auto block text-sm bg-blue-500 text-white rounded hover:bg-blue-600">
            Create
        </button>
    </form>
    <div class="font-[sans-serif] w-4/5 overflow-x-auto">
        <table class="min-w-full bg-white">
            <thead class="bg-gray-800 whitespace-nowrap">
            <tr>
                <th class="p-4 text-left text-sm font-medium text-white">
                    Id
                </th>
                <th class="p-4 text-left text-sm font-medium text-white">
                    Name
                </th>
                <th class="p-4 text-left text-sm font-medium text-white">
                    Created At
                </th>
                <th class="p-4 text-left text-sm font-medium text-white">
                    Actions
                </th>
            </tr>
            </thead>
            <tbody class="whitespace-nowrap">
            <c:forEach items="${requestScope.categories}" var="category">
                <tr class="even:bg-blue-50">
                    <td class="p-2 text-sm text-black">
                            ${category.getId()}
                    </td>
                    <td class="p-2 text-sm text-black">
                        <a href="<c:url value="/?id=${category.getId()}"/>">
                                ${category.getName()}
                        </a>
                    </td>
                    <td class="p-2 text-sm text-black">
                        2022-05-15
                    </td>
                    <td class="p-2 flex items-center gap-2">
                        <a href="categories/${category.getId()}">
                            <button class="mr-4" title="Edit">
                                <svg xmlns="http://www.w3.org/2000/svg" class="w-5 fill-blue-500 hover:fill-blue-700"
                                     viewBox="0 0 348.882 348.882">
                                    <path
                                            d="m333.988 11.758-.42-.383A43.363 43.363 0 0 0 304.258 0a43.579 43.579 0 0 0-32.104 14.153L116.803 184.231a14.993 14.993 0 0 0-3.154 5.37l-18.267 54.762c-2.112 6.331-1.052 13.333 2.835 18.729 3.918 5.438 10.23 8.685 16.886 8.685h.001c2.879 0 5.693-.592 8.362-1.76l52.89-23.138a14.985 14.985 0 0 0 5.063-3.626L336.771 73.176c16.166-17.697 14.919-45.247-2.783-61.418zM130.381 234.247l10.719-32.134.904-.99 20.316 18.556-.904.99-31.035 13.578zm184.24-181.304L182.553 197.53l-20.316-18.556L294.305 34.386c2.583-2.828 6.118-4.386 9.954-4.386 3.365 0 6.588 1.252 9.082 3.53l.419.383c5.484 5.009 5.87 13.546.861 19.03z"
                                            data-original="#000000"/>
                                    <path
                                            d="M303.85 138.388c-8.284 0-15 6.716-15 15v127.347c0 21.034-17.113 38.147-38.147 38.147H68.904c-21.035 0-38.147-17.113-38.147-38.147V100.413c0-21.034 17.113-38.147 38.147-38.147h131.587c8.284 0 15-6.716 15-15s-6.716-15-15-15H68.904C31.327 32.266.757 62.837.757 100.413v180.321c0 37.576 30.571 68.147 68.147 68.147h181.798c37.576 0 68.147-30.571 68.147-68.147V153.388c.001-8.284-6.715-15-14.999-15z"
                                            data-original="#000000"/>
                                </svg>
                            </button>
                        </a>
                        <form action="${pageContext.request.contextPath}/categories" class="my-10" method="post">
                            <input type="hidden" name="command" value="delete_category">
                            <input type="hidden" name="id" value=${category.getId()}>
                            <button class="mr-4" title="Delete">
                                <svg xmlns="http://www.w3.org/2000/svg" class="w-5 fill-red-500 hover:fill-red-700"
                                     viewBox="0 0 24 24">
                                    <path
                                            d="M19 7a1 1 0 0 0-1 1v11.191A1.92 1.92 0 0 1 15.99 21H8.01A1.92 1.92 0 0 1 6 19.191V8a1 1 0 0 0-2 0v11.191A3.918 3.918 0 0 0 8.01 23h7.98A3.918 3.918 0 0 0 20 19.191V8a1 1 0 0 0-1-1Zm1-3h-4V2a1 1 0 0 0-1-1H9a1 1 0 0 0-1 1v2H4a1 1 0 0 0 0 2h16a1 1 0 0 0 0-2ZM10 4V3h4v1Z"
                                            data-original="#000000"/>
                                    <path d="M11 17v-7a1 1 0 0 0-2 0v7a1 1 0 0 0 2 0Zm4 0v-7a1 1 0 0 0-2 0v7a1 1 0 0 0 2 0Z"
                                          data-original="#000000"/>
                                </svg>
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@include file="components/footer.jsp" %>
</body>
</html>
