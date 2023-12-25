<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ page import="com.example.flashcards.model.Collection" %> 
<% 
Collection collection = (Collection) request.getAttribute("collection");
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Collection - View</title>
    <%@ include file="../tailwind.jsp" %>
  </head>
  <body>
    <%= collection.getTitle() %>
    <%= collection.getCategory() %>
  </body>
</html>
