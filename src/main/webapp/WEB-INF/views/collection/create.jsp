<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Collection - Create</title>
    <%@ include file="../tailwind.jsp" %>
  </head>
  <body class="p-4 md:p-8 !pt-20">
    <div class="container bg-zinc-50 rounded-lg shadow-lg p-4 md:p-8">
      <form
        action="/collection/create"
        method="post"
        class="w-3/4 md:w-1/2 mx-auto"
      >
        <label for="title" class="mb-1 block">Title:</label>
        <input
          type="text"
          id="title"
          name="title"
          required
          autofocus
          class="px-4 py-2 w-full border border-gray-300 rounded-lg mb-4"
        />
        <label for="category" class="mb-1 block">Category:</label>
        <input
          type="text"
          id="category"
          name="category"
          required
          class="px-4 py-2 w-full border border-gray-300 rounded-lg mb-12"
        />
        <button
          class="block mx-auto rounded-full py-2 px-4 bg-blue-500 text-white hover:bg-blue-600 transition-all"
        >
          Create Collection
        </button>
      </form>
    </div>
  </body>
</html>
