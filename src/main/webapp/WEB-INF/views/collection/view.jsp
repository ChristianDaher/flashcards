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
      <div class="flex gap-4 flex-wrap text-white text-sm my-4">
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
      <div class="flex gap-4 flex-wrap my-4">
        <a href="/"
          ><button
            class="px-4 py-2 bg-blue-500 hover:bg-blue-600 transition-all text-white rounded-lg"
          >
            Go Back
          </button></a
        >
        <form
          action="/collection/<%= collection.getId() %>/reset"
          method="post"
        >
          <button
            class="px-4 py-2 bg-yellow-500 hover:bg-yellow-600 transition-all text-white rounded-lg"
          >
            Reset Flashcards
          </button>
        </form>
        <a href="/collection/<%= collection.getId() %>/play"
          ><button
            class="px-4 py-2 bg-purple-500 hover:bg-purple-600 transition-all text-white rounded-lg"
          >
            Ready To Play!
          </button></a
        >
      </div>
      <div
        class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4"
      >
        <button
          onclick="document.getElementById('addFlashcardDialog').showModal()"
          class="bg-white border border-gray-300 p-4 shadow-lg rounded-lg flex items-center justify-center min-h-44 transform transition duration-500 ease-in-out hover:scale-105"
        >
          <img src="/add.svg" alt="add" />
        </button>
        <% for (Flashcard flashcard : flashcards) { %>
        <div class="bg-white border border-gray-300 p-4 shadow-lg rounded-lg">
          <div class="inline-block mb-2 text-xs text-white rounded-lg px-4 py-2 <%= flashcard.getIsCorrect() == null ? "bg-gray-400" : (flashcard.getIsCorrect() ? "bg-green-500" : "bg-red-500") %>"><%= flashcard.getIsCorrect() == null ? "Unanswered" : (flashcard.getIsCorrect() ? "Correct" : "Wrong") %></div>
          <h3 class="font-bold mb-2"><%= flashcard.getQuestion() %></h3>
          <p><%= flashcard.getAnswer() %></p>
        </div>
        <% } %>
      </div>
    </div>
    <dialog id="addFlashcardDialog" class="bg-zinc-50 container shadow-lg rounded-lg p-6 !mt-4 md:!mt-8">
      <h3 class="text-2xl text-center mb-6">Add New Flashcard</h3>
      <form action="/collection/<%= collection.getId() %>/flashcard/create" method="post" class="w-3/4 mx-auto">
        <label for="question" class="mb-1 block">Question:</label>
        <input
          type="text"
          id="question"
          name="question"
          required
          autofocus
          class="px-4 py-2 w-full border border-gray-300 rounded-lg mb-4"
        />
        <label for="answer" class="mb-1 block">Answer:</label>
        <textarea
          id="answer"
          name="answer"
          required
          rows="4"
          class="px-4 py-2 w-full border border-gray-300 rounded-lg mb-6"
        ></textarea>
        <div class="flex justify-between items-center">
          <button class="text-white rounded-lg bg-red-500 hover:bg-red-600 px-4 py-2" onclick="cancelAddflashcard()">Cancel</button>
          <button class="text-white rounded-lg bg-blue-500 hover:bg-blue-600 px-4 py-2" type="submit">Create</button>
        </div>
      </form>
    </dialog>
  </body>
  <script>
    function cancelAddflashcard() {
      event.preventDefault(); 
      document.getElementById('question').value = "";
      document.getElementById('answer').value = "";
      document.getElementById('addFlashcardDialog').close();
    }
  </script>
</html>
