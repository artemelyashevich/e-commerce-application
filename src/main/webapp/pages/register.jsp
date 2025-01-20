<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--@elvariable id="userLocale" type="java.lang.String"--%>
<fmt:setLocale value="${userLocale}" />
<html>
<head>
    <title>Register</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<fmt:setLocale value="ru_RU" />
<fmt:setBundle basename="message" />
<%@include file="components/header.jsp" %>
<div class="h-full flex items-center justify-center">
    <form action="${pageContext.request.contextPath}/register" method="post" class="font-[sans-serif] m-6 w-2/5 mx-auto">
        <input type="hidden" name="command" value="register_action">
        <h1 class="text-center text-2xl font-bold mb-7">
            <fmt:message key="message.btn.register" />
        </h1>
        <div class="grid sm:grid-cols-2 gap-10">
            <div class="relative flex items-center">
                <label class="text-[13px] bg-white text-black absolute px-2 top-[-10px] left-[18px]">
                    <fmt:message key="message.label.full_name" />
                </label>
                <input name="fullName" type="text" placeholder="Enter first name"
                       class="px-4 py-3.5 bg-white text-black w-full text-sm border-2 border-gray-100 focus:border-blue-500 rounded outline-none"/>
                <svg xmlns="http://www.w3.org/2000/svg" fill="#bbb" stroke="#bbb"
                     class="w-[18px] h-[18px] absolute right-4"
                     viewBox="0 0 24 24">
                    <circle cx="10" cy="7" r="6" data-original="#000000"></circle>
                    <path
                            d="M14 15H6a5 5 0 0 0-5 5 3 3 0 0 0 3 3h12a3 3 0 0 0 3-3 5 5 0 0 0-5-5zm8-4h-2.59l.3-.29a1 1 0 0 0-1.42-1.42l-2 2a1 1 0 0 0 0 1.42l2 2a1 1 0 0 0 1.42 0 1 1 0 0 0 0-1.42l-.3-.29H22a1 1 0 0 0 0-2z"
                            data-original="#000000"></path>
                </svg>
            </div>
            <div class="relative flex items-center">
                <label class="text-[13px] bg-white text-black absolute px-2 top-[-10px] left-[18px]">Username</label>

                <input name="username" type="text" placeholder="Enter username"
                       class="px-4 py-3.5 bg-white text-black w-full text-sm border-2 border-gray-100 focus:border-blue-500 rounded outline-none"/>
                <svg xmlns="http://www.w3.org/2000/svg" fill="#bbb" stroke="#bbb"
                     class="w-[18px] h-[18px] absolute right-4"
                     viewBox="0 0 24 24">
                    <circle cx="10" cy="7" r="6" data-original="#000000"></circle>
                    <path
                            d="M14 15H6a5 5 0 0 0-5 5 3 3 0 0 0 3 3h12a3 3 0 0 0 3-3 5 5 0 0 0-5-5zm8-4h-2.59l.3-.29a1 1 0 0 0-1.42-1.42l-2 2a1 1 0 0 0 0 1.42l2 2a1 1 0 0 0 1.42 0 1 1 0 0 0 0-1.42l-.3-.29H22a1 1 0 0 0 0-2z"
                            data-original="#000000"></path>
                </svg>
            </div>
            <div class="relative flex items-center sm:col-span-2">
                <label class="text-[13px] bg-white text-black absolute px-2 top-[-10px] left-[18px]">
                    <fmt:message key="message.country" />
                </label>
                <input name="address" type="text" placeholder="Enter country"
                       class="px-4 py-3.5 bg-white text-black w-full text-sm border-2 border-gray-100 focus:border-blue-500 rounded outline-none"/>
                <svg xmlns="http://www.w3.org/2000/svg" fill="#bbb" class="w-[18px] h-[18px] absolute right-4"
                     viewBox="0 0 24 24">
                    <path
                            d="M20.48 8.301A9.217 9.217 0 0 1 21.25 12c0 5.105-4.145 9.25-9.25 9.25S2.75 17.105 2.75 12 6.895 2.75 12 2.75a.75.75 0 0 0 0-1.5C6.067 1.25 1.25 6.067 1.25 12S6.067 22.75 12 22.75 22.75 17.933 22.75 12c0-1.529-.32-2.983-.896-4.301a.75.75 0 0 0-1.374.602z"
                            data-original="#000000"/>
                    <path
                            d="M17 1.25a3.443 3.443 0 0 0-3.442 3.442c0 .594.269 1.317.685 2.023.835 1.421 2.227 2.815 2.227 2.815a.749.749 0 0 0 1.06 0s1.392-1.394 2.227-2.815c.416-.706.685-1.429.685-2.023 0-1.9-1.542-3.442-3.442-3.442zm0 1.5c1.072 0 1.942.87 1.942 1.942 0 .528-.393 1.177-.815 1.789A15.328 15.328 0 0 1 17 7.901c-.325-.366-.75-.874-1.127-1.42-.422-.612-.815-1.261-.815-1.789 0-1.072.87-1.942 1.942-1.942zM1.603 12.636l3.27 2.044c.596.372 1.285.57 1.987.57h.76c.657 0 1.281.287 1.709.786l1.051 1.227a2.25 2.25 0 0 1 .456 2.082l-.557 1.949a.75.75 0 0 0 1.442.412l.557-1.949a3.748 3.748 0 0 0-.759-3.47l-1.052-1.227a3.746 3.746 0 0 0-2.847-1.31h-.76c-.421 0-.834-.118-1.192-.342l-3.271-2.044a.75.75 0 1 0-.794 1.272z"
                            data-original="#000000"/>
                    <path
                            d="m5.329 4.335 1.596 3.192a2.748 2.748 0 0 0 1.757 1.429l1.758.465c.443.117.786.467.894.912l.753 3.087a2.75 2.75 0 0 0 1.146 1.637l.466.31a2.746 2.746 0 0 0 3.985-1.058l.575-1.151a1.25 1.25 0 0 1 .815-.653l2.791-.698a.75.75 0 0 0-.364-1.455l-2.791.697a2.752 2.752 0 0 0-1.792 1.438l-.576 1.151a1.246 1.246 0 0 1-1.811.481l-.466-.31a1.25 1.25 0 0 1-.521-.744l-.752-3.087a2.75 2.75 0 0 0-1.969-2.007l-1.758-.465a1.247 1.247 0 0 1-.798-.65L6.671 3.665a.75.75 0 1 0-1.342.67z"
                            data-original="#000000"/>
                </svg>
            </div>
            <div class="relative flex items-center sm:col-span-2">
                <label class="text-[13px] bg-white text-black absolute px-2 top-[-10px] left-[18px]">
                    <fmt:message key="message.enter.email" />
                </label>
                <input name="email" type="email" placeholder="Enter email"
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
                    <fmt:message key="message.enter.password" />
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
        <a href="${pageContext.request.contextPath}/login" class="w-full flex justify-center">
            <p class="my-2 text-m text-blue-700 underline">
                <fmt:message key="message.login" />
            </p>
        </a>
    </form>
</div>
<%@include file="components/footer.jsp" %>
</body>
</html>
