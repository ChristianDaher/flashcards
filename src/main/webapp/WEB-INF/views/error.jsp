<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<% 
String errorMessage = (String) request.getAttribute("errorMessage");
String backLink = (String) request.getAttribute("backLink");
if (backLink == null || backLink.isEmpty()) {
  backLink = "/";
}
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- This is the error page, it takes an error message and a back link as parameters to pass -->
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Error</title>
    <%@ include file="tailwind.jsp" %>
  </head>
  <body class="flex items-center justify-center h-screen">
    <div class="text-center">
      <h1 class="text-6xl text-red-500 mb-4 tracking-wide">ERROR</h1>
      <h2 class="text-2xl mb-8" id="errorMessage"></h2>
      <a
        href="<%= backLink %>"
        class="rounded-full py-2 px-4 bg-blue-500 text-white hover:bg-blue-600 transition-all"
      >
        Go Back
      </a>
    </div>
  </body>
  <script>
    document.getElementById("errorMessage").innerHTML = "<%= errorMessage %>";
  </script>
</html>
