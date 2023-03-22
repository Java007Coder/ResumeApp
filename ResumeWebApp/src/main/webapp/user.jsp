<%-- 
    Document   : user
    Created on : Mar 3, 2023, 11:55:23 PM
    Author     : Ravan
--%>

<%@page import="com.mycompany.entity.User"%>
<%@page import="com.mycompany.main.Context"%>
<%@page import="com.mycompany.dao.inter.UserDaoInter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            UserDaoInter userDao = Context.instanceUserDao();
            User u = userDao.getById(1);
        %>

        <div>
            <form action = "UserController" method = "POST">
                <input type = "hidden" name = "id" value = "<%=u.getId()%>">
                <label for = "name">name:</label>
                <input type = "text" name = "name" value = "<%=u.getName()%>"/>
                <br/>
                <label for = "surname">surname:</label>
                <input type = "text" name = "surname" value = "<%=u.getSurname()%>"/>

                <input type = "submit" name = "save" value = "Save">
            </form>
        </div>
    </body>
</html>
