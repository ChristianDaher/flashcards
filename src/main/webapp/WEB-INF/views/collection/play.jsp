<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ page import="com.example.flashcards.model.Flashcard" %> 
<% 
Flashcard flashcard = (Flashcard) request.getAttribute("flashcard");
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Collection - Play</title>
    <%@ include file="../tailwind.jsp" %>
  </head>
  <body>
    <%= flashcard.getQuestion() %>
    <%= flashcard.getAnswer() %>
  </body>
</html>
