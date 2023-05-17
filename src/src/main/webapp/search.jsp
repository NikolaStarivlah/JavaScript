<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/include/header.jsp" %>
<h1>Search Your Plan</h1>
<hr>
<h3>${errormessage}</h3>
<form action="searchid" method="POST">
    <table>
        <tr>
            <td>Category:</td>
            <td>
                <select name="activityCategory">
                    <option>All</option>
                    <c:forEach var="activity" items="${activities}">
                        <option>${activity}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Search"/>
            </td>
        </tr>
    </table>
</form>
<h3>${books}</h3>
<hr>
<a href="index.jsp">Go Add</a>
<%@include file="/include/footer.jsp" %>