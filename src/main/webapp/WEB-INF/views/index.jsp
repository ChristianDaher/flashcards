<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ page import="com.example.flashcards.model.User" %> 
<%@ page import="com.example.flashcards.model.Collection" %> 
<%@ page import="java.util.List" %> 
<%@ include file="tailwind.jsp" %> 
<% 
User user = (User) request.getAttribute("user");
List<Collection> collections = (List<Collection>) request.getAttribute("collections"); 
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>My Collections</title>
  </head>
  <body>
    <h1 class="text-2xl">Welcome Back, <%= user.getName() %>!</h1>
      <% if (collections.isEmpty()) { %>
      <img src="/flashcards.svg" alt="flashcards" />
      <p>Get started with your first collection</p>
      <button>Create Collection</button>
      <% } else { %>
      <ul>
        <% for (Collection collection : collections) { %>
        <li><%= collection.getTitle() %></li>
        <% } %>
      </ul>
      <% } %>
  </body>
</html>
