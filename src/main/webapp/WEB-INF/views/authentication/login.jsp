<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<% 
String errorMessage = (String) request.getAttribute("errorMessage");
if(errorMessage == null) {
    errorMessage = "";
}
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Error</title>
    <%@ include file="../tailwind.jsp" %>
  </head>
  <body>
    <div>
      LOGIN
    </div>
  </body>
</html>
