<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ page import="com.example.flashcards.model.User" %> 
<%@ page import="com.example.flashcards.model.Collection" %> 
<%@ page import="java.util.List" %> 
<%@ page import="java.util.Map" %> 
<% 
User user = (User) request.getAttribute("user");
Map<String, List<Collection>> groupedCollections = (Map<String, List<Collection>>) request.getAttribute("groupedCollections");
String bodyClass = groupedCollections.isEmpty() ? "justify-center" : "flex-col gap-6";
%>
<%!
public String getRandomColor() {
  List<String> colors = List.of("red", "orange", "amber", "yellow", "lime", "green", "emerald", "teal", "cyan", "sky", "blue", "indigo", "violet", "purple", "fuchsia", "pink", "rose");
  List<String> shades = List.of("300", "400", "500", "600", "700", "800", "900", "950");
  String color = colors.get((int) (Math.random() * colors.size()));
  String shade = shades.get((int) (Math.random() * shades.size()));
  return "bg-" + color + "-" + shade;
}
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>My Collections</title>
    <%@ include file="tailwind.jsp" %>
  </head>
  <body class="flex min-h-screen p-4 sm:p-8 items-center <%= bodyClass %>">
    <% if (groupedCollections.isEmpty()) { %>
    <div class="p-8 bg-zinc-50 rounded-lg shadow-lg text-center">
      <h1 class="text-2xl">Welcome back, <%= user.getName() %>!</h1>
      <img src="/flashcards.svg" alt="flashcards" class="mx-auto my-6" />
      <h2 class="text-xl font-bold">Flash Cards</h2>
      <p class="mt-2 mb-6 text-sm">Get started with your first collection</p>
      <a href="/collection/create">
        <button
          class="rounded-lg py-2 px-4 bg-blue-500 text-white hover:bg-blue-600 transition-all"
        >
          Create Collection
        </button>
      </a>
    </div>
    <% } else { %>
    <div class="container bg-zinc-50 rounded-lg shadow-lg text-center py-4">
      <h1 class="text-2xl text-center">Welcome back, <%= user.getName() %>!</h1>
    </div>
    <div class="container grow">
      <ul class="bg-zinc-50 rounded-lg shadow-lg">
        <% for (Map.Entry<String, List<Collection>> entry : groupedCollections.entrySet()) { %>
          <% String category = entry.getKey(); %>
          <% String bgColor = getRandomColor(); %>
          <% List<Collection> collections = entry.getValue(); %>
          <% for (Collection collection : collections) { %>
            <li class="grid grid-cols-12 gap-4 px-4 py-2 items-center">
              <div class="col-span-7 grid grid-cols-3 gap-4 items-center">
                <div class="col-span-1 text-center font-semibold px-4 py-2 rounded-full text-sm truncate min-w-16 <%= bgColor %> text-white">
                  <%= category %>
                </div>
                <div class="col-span-2 px-4 py-2 truncate">
                  <%= collection.getTitle() %>
                </div>
              </div>
              <div class="col-span-1 text-black text-xs rounded-full bg-gray-200 h-8 w-8 flex items-center justify-center">
                <%= collection.getFlashcardCount() %>
              </div>
              <div class="col-span-4 flex items-center gap-4 justify-self-end">
                <a href="/collection/<%= collection.getId() %>" class="rounded-full bg-gray-200 h-8 w-8 flex items-center justify-center hover:bg-gray-300 transition-all"><img src="/edit.svg" alt="edit"></a>
                <a href="/collection/<%= collection.getId() %>/play" class="flex items-center justify-center"><img src="/arrow.svg" alt="play"></a>
              </div>
            </li>
          <% } %>
      <% } %>
      </ul>
    </div>
    <a href="/collection/create" class="my-4">
      <button
        class="rounded-lg py-2 px-4 bg-blue-500 text-white hover:bg-blue-600 transition-all"
      >
        Add Collection
      </button>
    </a>
    <% } %>
  </body>
</html>
