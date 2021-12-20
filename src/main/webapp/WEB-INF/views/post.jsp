<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<html>
<body>


<a href="<c:url value='/'/>">Назад</a>

<h1>TOPIC: ${post.name}</h1>
<h2>SUBJECT: ${post.description}</h2>
<h4>TOPIC STARTER: ${userName}</h4>
<h5>Created: ${post.created}</h5>

<table class="table table-striped table-dark">
    <thead>
    <tr>
        <th scope="col">Message#</th>
        <th scope="col">From</th>
        <th scope="col">Text</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th scope="row">1</th>
        <td>1</td>
        <td>Alina</td>
        <td>AmazingTitle!</td>
    </tr>
    </tbody>
</table>
</body>
</html>