<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--@elvariable id="userLocale" type="java.lang.String"--%>
<fmt:setLocale value="${userLocale}" />
<fmt:setBundle basename="resource.message" var="rb"/>
<html>
<head>
    <title>Login</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<fmt:setLocale value="ru_RU" />
<fmt:setBundle basename="message" />
<%@include file="components/header.jsp" %>
<div class="h-full flex items-center justify-center">
    <form method="post" action="${pageContext.request.contextPath}/login" class="font-[sans-serif] m-6 w-2/5 mx-auto">
        <input type="hidden" name="command" value="login_action">
        <h1 class="text-center text-2xl font-bold mb-7">
            <fmt:message key="message.btn.login" />
        </h1>
        <div class="grid sm:grid-cols-2 gap-10">
            <div class="relative flex items-center sm:col-span-2">
                <label class="text-[13px] bg-white text-black absolute px-2 top-[-10px] left-[18px]">
                    <fmt:message key="message.email" />
                </label>
                <input type="email" name="email" placeholder="Enter email"
                       class="px-4 py-3.5 bg-white text-black w-full text-sm border-2 border-gray-100 focus:border-blue-500 rounded outline-none"/>
                <svg xmlns="http://www.w3.org/2000/svg" fill="#bbb" stroke="#bbb"
                     class="w-[18px] h-[18px] absolute right-4"
                     viewBox="0 0 682.667 682.667">
                    <defs>
                        <clipPath id="a" clipPathUnits="userSpaceOnUse">
                            <path d="M0 512h512V0H0Z" data-original="#000000"></path>
                        </clipPath>
                    </defs>
                    <g clip-path="url(#a)" transform="matrix(1.33 0 0 -1.33 0 682.667)">
                        <path fill="none" stroke-miterlimit="10" stroke-width="40"
                              d="M452 444H60c-22.091 0-40-17.909-40-40v-39.446l212.127-157.782c14.17-10.54 33.576-10.54 47.746 0L492 364.554V404c0 22.091-17.909 40-40 40Z"
                              data-original="#000000"></path>
                        <path
                                d="M472 274.9V107.999c0-11.027-8.972-20-20-20H60c-11.028 0-20 8.973-20 20V274.9L0 304.652V107.999c0-33.084 26.916-60 60-60h392c33.084 0 60 26.916 60 60v196.653Z"
                                data-original="#000000"></path>
                    </g>
                </svg>
            </div>
            <div class="relative flex items-center sm:col-span-2">
                <label
                        class="text-[13px] bg-white text-black absolute px-2 top-[-10px] left-[18px]">
                    <fmt:message key="message.password" />
                </label>
                <input name="password" type="password" autocomplete="new-password" placeholder="Enter password"
                       class="px-4 py-3.5 bg-white text-black w-full text-sm border-2 border-gray-100 focus:border-blue-500 rounded outline-none"/>
                <svg xmlns="http://www.w3.org/2000/svg" fill="#bbb" stroke="#bbb"
                     class="w-[18px] h-[18px] absolute right-4 cursor-pointer" viewBox="0 0 128 128">
                    <path
                            d="M64 104C22.127 104 1.367 67.496.504 65.943a4 4 0 0 1 0-3.887C1.367 60.504 22.127 24 64 24s62.633 36.504 63.496 38.057a4 4 0 0 1 0 3.887C126.633 67.496 105.873 104 64 104zM8.707 63.994C13.465 71.205 32.146 96 64 96c31.955 0 50.553-24.775 55.293-31.994C114.535 56.795 95.854 32 64 32 32.045 32 13.447 56.775 8.707 63.994zM64 88c-13.234 0-24-10.766-24-24s10.766-24 24-24 24 10.766 24 24-10.766 24-24 24zm0-40c-8.822 0-16 7.178-16 16s7.178 16 16 16 16-7.178 16-16-7.178-16-16-16z"
                            data-original="#000000"></path>
                </svg>
            </div>
        </div>
        <button type="submit"
                class="mt-8 px-6 py-2.5 w-full text-sm bg-blue-500 text-white rounded hover:bg-blue-600 transition-all">
            <fmt:message key="message.btn.submit" />
        </button>
        <a href="${pageContext.request.contextPath}/register" class="w-full flex justify-center">
            <p class="my-2 text-m text-blue-700 underline">
                <fmt:message key="message.register" />
            </p>
        </a>
    </form>
</div>
<%@include file="components/footer.jsp" %>
</body>
</html>
