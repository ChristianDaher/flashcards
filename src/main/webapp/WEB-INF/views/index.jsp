<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="com.example.flashcards.model.Collection" %>
<%@ page import="java.util.List" %>
<%@ include file="tailwind.jsp" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>My Collections</title>
  </head>
  <body>
    <h1 class="text-3xl text-red-500">Welcome Back, ${user.name}!</h1>
    <!-- <h2>My Collections</h2> -->
    <img src="/flashcards.svg" alt="flashcards">
    <!-- <ul>
      <% 
      List<Collection> collections = (List<Collection>) request.getAttribute("collections");
      for (Collection collection : collections) { %>
      <li><%= collection.getTitle() %></li>
      <% } %>
    </ul> -->
  </body>
</html>
