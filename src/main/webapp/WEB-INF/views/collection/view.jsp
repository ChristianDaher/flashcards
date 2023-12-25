<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ page import="com.example.flashcards.model.Collection" %> 
<%@ page import="com.example.flashcards.model.Flashcard" %> 
<%@ page import="java.util.List" %> 
<% 
Collection collection = (Collection) request.getAttribute("collection");
List<Flashcard> flashcards = (List<Flashcard>) request.getAttribute("flashcards");
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
    <%= collection.getFlashcardCount() %>
    <%= collection.getAnsweredFlashcardCount() %>
    <%= collection.getCorrectlyAnsweredFlashcardCount() %>
    <% for (Flashcard flashcard : flashcards) { %>
      <%= flashcard.getQuestion() %>
      <%= flashcard.getAnswer() %>
    <% } %>
  </body>
</html>
