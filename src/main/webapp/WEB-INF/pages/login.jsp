<%--
  Created by IntelliJ IDEA.
  User: shind
  Date: 8/21/2019
  Time: 8:31 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    if(session.getAttribute("username")!=null){
        response.sendRedirect("adminHomepage.html");
    }
%>

<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body class="container bg-dark" style="margin-top: 100px; max-width: 500px;">

<%
    if (request.getAttribute("message") != null) {
        out.print("<div class=\"card border-dark mb-3\" style=\"border-radius: 25px;border: 2px solid #73AD21;\">\n" +
                "    <div class=\"card-header\">\n" +
                "        Error" +
                "    </div>\n" +
                "    <div class=\"card-body\">");
        out.println(request.getAttribute("message"));
        out.print("</div></div>");
        request.removeAttribute("message");
    }
%>

<%
    if (request.getAttribute("logoutMessage") != null) {
        out.print("<div class=\"card border-dark mb-3\" style=\"border-radius: 25px;border: 2px solid #73AD21;\">\n" +
                "    <div class=\"card-header\">\n" +
                "        Message" +
                "    </div>\n" +
                "    <div class=\"card-body\">");
        out.println(request.getAttribute("logoutMessage"));
        out.print("</div></div>");
        request.removeAttribute("logoutMessage");
    }
%>

<div class="card border-dark mb-3" style="border-radius: 25px;border: 2px solid #73AD21;">
    <div class="card-header">
        Admin Login
    </div>
    <div class="card-body">

        <form:form modelAttribute="admin" action="verifyAdmin.html" method="post">
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">Username</span>
                </div>
                <form:input cssClass="form-control" path="username" placeholder="Username"></form:input>
            </div>

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">Password</span>
                </div>

                <form:input type="password" cssClass="form-control" path="password" placeholder="password"></form:input>
            </div>
            <div class="form-group form-check">
                <label class="form-check-label">
                    <input class="form-check-input" type="checkbox"> Remember me
                </label>
            </div>
            <hr>
            <input type="submit" class="btn btn-primary" style="width: 321px" value="Login">
            <button type="button" class="btn btn-secondary" style="width: 100px">Cancel</button>
        </form:form>
    </div>
</div>
</body>

</html>
