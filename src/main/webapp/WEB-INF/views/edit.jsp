<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<html>
<body>

<td>
    <a href="<c:url value='/'/>">Назад</a>
</td>

<form action="<c:url value='/save'/>" method='POST'>

    <input type="hidden" name='id'
           value="${editedPost.id}">
    <table>
        <tr>
            <th>Name</th>
            <td><input type='text' name='name' value="${editedPost.name}"></td>
            <th>Description</th>
            <td><input type='text' name='description' value="${editedPost.description}"></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить"/></td>
        </tr>
    </table>
</form>
</body>
</html>