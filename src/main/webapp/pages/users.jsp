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
<div class="flex flex-col justify-center items-center gap-5 my-10">
    <h1 class="mt-3 font-bold text-2xl">Users</h1>
    <div class="font-[sans-serif] w-4/5 overflow-x-auto">
        <table class="min-w-full bg-white">
            <thead class="bg-gray-800 whitespace-nowrap">
            <tr>
                <th class="p-4 text-left text-sm font-medium text-white">
                    Id
                </th>
                <th class="p-4 text-left text-sm font-medium text-white">
                    Username
                </th>
                <th class="p-4 text-left text-sm font-medium text-white">
                    Email
                </th>
                <th class="p-4 text-left text-sm font-medium text-white">
                    Full name
                </th>
                <th class="p-4 text-left text-sm font-medium text-white">
                    Address
                </th>
                <th class="p-4 text-left text-sm font-medium text-white">
                    Role
                </th>
                <th class="p-4 text-left text-sm font-medium text-white">
                    Actions
                </th>
            </tr>
            </thead>
            <tbody class="whitespace-nowrap">
            <c:forEach items="${requestScope.users}" var="user">
                <tr class="even:bg-blue-50">
                    <td class="p-2 text-sm text-black">
                            ${user.getId()}
                    </td>
                    <td class="p-2 text-sm text-black">
                        <a href="<c:url value="/?id=${user.getId()}"/>">
                                ${user.getUsername()}
                        </a>
                    </td>
                    <td class="p-2 text-sm text-black">
                            ${user.getEmail()}
                    </td>
                    <td class="p-2 text-sm text-black">
                            ${user.getFullName()}
                    </td>
                    <td class="p-2 text-sm text-black">
                            ${user.getAddress()}
                    </td>
                    <td class="p-2 text-sm text-black">
                            ${user.getRole()}
                    </td>
                    <td class="p-2 flex items-center gap-2">
                        <form action="${pageContext.request.contextPath}/users" class="my-10" method="post">
                            <input type="hidden" name="command" value="delete_user_action">
                            <input type="hidden" name="userId" value=${user.getId()}>
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
