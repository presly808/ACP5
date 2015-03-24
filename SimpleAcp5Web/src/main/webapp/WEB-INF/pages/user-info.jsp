<%@ include file="include.jsp"%>

<html>
<head>
    <title></title>
</head>
<body>

    <%@ include file="menu-list.html"%>

    <c:set var="myUser" value="${applicationScope.userA}"/>

    <h2>User info</h2>
    id : <c:out value="${myUser.id}"/> <br/>
    name : <c:out value="${myUser.name}"/> <br/>
    age : <c:out value="${myUser.age}"/> <br/>


</body>

</html>
