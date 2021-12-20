<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>Форум job4j</title>
</head>

<body>
<div>
    Login as : ${userName} from timezone: ${timeZone}  <a href="<c:url value='/logout'/>">Выйти</a>
</div>
<a href="<c:url value='/edit'/>">Добавить пост</a>
<table class="table table-striped">
    <thead>
    <tr>
        <th>#</th>
        <th>Name</th>
        <th>Description</th>
        <th>DateOfCreated</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${posts}" var="post">
        <tr>
            <th scope="row"></th>
            <td><a href="<c:url value='/post?id=${post.id}'/>">${post.name}</a></td>
            <td>${post.description}</td>
            <td>${post.created}</td>
            <td>
                <a href="<c:url value='/edit?id=${post.id}'/>">Изменить</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>