<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Collection - Not Found</title>
    <%@ include file="../tailwind.jsp" %>
  </head>
  <body class="flex items-center justify-center h-screen">
    <div class="text-center">
      <h1 class="text-6xl text-red-500 mb-4">404</h1>
      <h2 class="text-2xl mb-8">Collection id not found</h2>
      <a
        href="/"
        class="rounded-full py-2 px-4 bg-blue-500 text-white hover:bg-blue-600 transition-all"
      >
        Go Back
      </a>
    </div>
  </body>
</html>