<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.flashcards.model.Flashcard" %>
<% Flashcard flashcard = (Flashcard) request.getAttribute("flashcard"); %>
<!DOCTYPE html>
<html>
  <head>
    <!-- This is the page you see when you click on a collection to play it -->
    <meta charset="UTF-8" />
    <title>Collection - Play</title>
    <%@ include file="../tailwind.jsp" %>
  </head>
  <body class="h-screen flex items-start justify-center p-8">
    <button
      onclick="goBack()"
      class="hover:bg-gray-200 h-8 w-8 rounded-full transition-all flex items-center justify-center"
    >
      <img src="/arrow.svg" alt="back" class="rotate-180" />
    </button>
    <div
      class="bg-zinc-50 rounded-lg shadow-lg p-8 container text-center mt-20"
    >
      <h1 class="text-3xl font-bold"><%= flashcard.getQuestion() %></h1>
      <p class="text-sm hidden mt-8" id="answer">
        <%= flashcard.getAnswer() %>
      </p>
    </div>
    <div
      class="absolute bottom-12 flex items-center justify-between container p-8"
    >
      <button
        onclick="markAnswer(false)"
        class="text-white rounded-lg bg-red-500 hover:bg-red-600 px-4 py-2"
      >
        Mark Wrong
      </button>
      <button
        onclick="toggleAnswer(this)"
        class="text-white rounded-lg bg-blue-500 hover:bg-blue-600 px-4 py-2"
      >
        Show Answer
      </button>
      <button
        onclick="markAnswer(true)"
        class="text-white rounded-lg bg-green-500 hover:bg-green-600 px-4 py-2"
      >
        Mark Correct
      </button>
    </div>
  </body>

  <script>
    function toggleAnswer(button) {
      document.getElementById("answer").classList.toggle("hidden");
      button.innerText =
        button.innerText === "Show Answer" ? "Hide Answer" : "Show Answer";
    }

    function goBack() {
      const previous = document.referrer;
      if (
        previous == null ||
        previous == "" ||
        previous == window.location.href
      )
        window.location.href = "/";
      else window.location.href = previous;
    }

    function markAnswer(answer) {
      fetch(
        "/collection/<%= flashcard.getCollectionId() %>/flashcard/<%= flashcard.getId() %>/answer",
        {
          method: "PATCH",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            answer: answer,
          }),
        }
      )
        .then((response) => {
          if (response.ok) {
            window.location.reload();
          }
        })
        .catch(() => {
          alert("Something went wrong");
        });
    }
  </script>
</html>
