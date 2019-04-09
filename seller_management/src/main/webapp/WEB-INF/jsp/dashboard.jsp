<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="false"%>
<html>
<head>
<title>Show Users</title>
</head>
<body>    
	<h3 style="color: red;">Show All Users</h3>
	<ul>
		<c:forEach var="userList" items="${allUsers}">
			<li>${userList}</li>
		</c:forEach>
	</ul>
</body>
</html>