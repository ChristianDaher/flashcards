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
    <div class="p-4 sm:p-8 !pb-0">
      <h1 class="text-3xl"><%= collection.getTitle() %></h1>
      <span
        class="inline-block mt-2 bg-blue-500 px-4 py-2 text-white rounded-full text-sm"
        ><%= collection.getCategory() %></span
      >
      <div class="flex gap-4 text-white text-sm my-4">
        <div class="rounded-lg bg-gray-400 px-4 py-2">
          <%= collection.getFlashcardCount() %> <%=
          collection.getFlashcardCount() == 1 ? "flashcard" : "flashcards" %> in
          this collection
        </div>
        <div class="rounded-lg bg-red-500 px-4 py-2">
          <%= collection.getAnsweredFlashcardCount() %> answered
        </div>
        <div class="rounded-lg bg-green-500 px-4 py-2">
          <%= collection.getCorrectlyAnsweredFlashcardCount() %> correctly
          answered
        </div>
      </div>
    </div>
    <hr class="border-gray-300" />
    <div class="p-4 sm:p-8 !pt-0">
      <div class="flex gap-4">
        <a href="/"><button class="my-4 px-4 py-2 bg-blue-500 hover:bg-blue-600 transition-all text-white rounded-lg">Go Back</button></a>
        <form action="/collection/<%= collection.getId() %>/reset" method="post">
          <button
          class="my-4 px-4 py-2 bg-yellow-500 hover:bg-yellow-600 transition-all text-white rounded-lg"
          >
          Reset Flashcards
        </button>
      </form>
      <a href="/collection/<%= collection.getId() %>/play"><button class="my-4 px-4 py-2 bg-purple-500 hover:bg-purple-600 transition-all text-white rounded-lg">Ready To Play!</button></a>
    </div>
      <div
        class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4"
      >
        <div
          class="bg-white border border-gray-300 p-4 shadow-lg rounded-lg flex items-center justify-center"
        ></div>
        <% for (Flashcard flashcard : flashcards) { %>
        <div class="bg-white border border-gray-300 p-4 shadow-lg rounded-lg">
          <h3 class="font-bold mb-2"><%= flashcard.getQuestion() %></h3>
          <p><%= flashcard.getAnswer() %></p>
        </div>
        <% } %>
      </div>
    </div>
  </body>
</html>
