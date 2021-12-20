<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<html>
<body>
<script>
    $(document).ready(
        function () {
            let offSet = new Date().getTimezoneOffset();

            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/timeZone',
                data: {
                    "timeOffset": offSet
                },
            }).fail(function (err) {
                console.log(err);
            });
        });
</script>

<a href="<c:url value='/reg'/>">Регистрация</a>
<c:if test="${not empty errorMessage}">
    <div style="color:red; font-weight: bold; margin: 30px 0px;">
            ${errorMessage}
    </div>
</c:if>
<form name='login' action="<c:url value='/login'/>" method='POST'>
    <table>
        <tr>
            <td>UserName:</td>
            <td><input type='text' name='username'></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password'/></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="submit"/></td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>