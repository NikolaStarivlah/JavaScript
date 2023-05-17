<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/include/header.jsp" %>
<h1>Make Your Summer Plan</h1>
<hr>
<h3>${errormessage}</h3>
<h3>${insert}</h3>
<form action="insertplan" method="POST">
    <table>
        <tr>
            <td>ID:</td>
            <td><input type="text" name="id"/></td>
        </tr>
        <tr>
            <td>Activity:</td>
            <td><input type="text" name="activity"></td>
        </tr>
        <tr>
            <td>Category:</td>
            <td>
                <select name="activityCategory">
                    <c:forEach var="activity" items="${activities}">
                        <option>${activity}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Add to plan"/>
            </td>
        </tr>
    </table>
</form>
<hr>
<a href="search.jsp">Go Search</a>
<%@include file="/include/footer.jsp" %>