<%-- 
    Document   : user
    Created on : Mar 3, 2023, 11:55:23 PM
    Author     : Ravan
--%>

<%@page import="com.mycompany.entity.User" %>
<%@page import="com.mycompany.main.Context" %>
<%@page import="com.mycompany.dao.inter.UserDaoInter" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<body>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="assets/css/users.css">
    <script src="https://kit.fontawesome.com/c9c7515dc1.js" crossorigin="anonymous"></script>
    <title>JSP Page</title>
</head>
    <%
UserDaoInter userDao = Context.instanceUserDao();
String name = request.getParameter("name");
String surname = request.getParameter("surname");
String nationalityIdStr = request.getParameter("nationality_id");
Integer nationalityId = null;
if(nationalityIdStr!=null && !nationalityIdStr.trim().isEmpty()){
    nationalityId = Integer.parseInt(nationalityIdStr);
}

List<User> list = userDao.getAll(name, surname, nationalityId);
    %>
<div class="style">
    <div class="control_panel">
        <form action="users.jsp" method="Get">
            <div class="control_panel_group">
                <label for="name">name:</label>
                <input type="text" name="name" value=""/>
            </div>
            <div class="control_panel_group">
                <label for="surname">surname:</label>
                <input type="text" name="surname" value=""/>
            </div>
            <input type="submit" name="search" value="Search">
        </form>
    </div>
    <div>
        <table>
            <thead>
            <tr>
                <th>name</th>
                <th>surname</th>
                <th>nationality</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <%
                for (User u : list) {
            %>
            <tr>
                <td><%=u.getName()%>
                </td>
                <td><%=u.getSurname()%>
                </td>
                <td><%=u.getNationality() == null ? "N/A" : u.getNationality()%>
                </td>
                <td>
                    <input style=" margin: 0 auto;
                           width: 50%;
                           border: 1px solid #b59e9e;" type="submit" name="action" value="delete"/>
                    <%--                        <i class="fa-sharp fa-solid fa-user-minus"></i>--%>
                    <input class="btn_table" type="submit" name="action" value="update"/>
                    <%--                        <i class="fa-solid fa-user-plus"></i>--%>
                    <%--                    </input>--%>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>

    </div>
</div>
</body>
</html>
