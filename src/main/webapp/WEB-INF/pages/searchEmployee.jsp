<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>search Employee</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<body class=" bg-dark">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="adminHomepage.html" style="margin-left:210px"><h4>Employee management</h4></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto"></ul>
        <form action="logout.html" method="post">
            <input type="submit" class="btn btn-danger" value="Logout" style="margin-right: 210px">
        </form>
    </div>
</nav>

<div class="container" style="margin-top: 40px;">
    <%
        if (request.getAttribute("errorMessage") != null) {
            out.print("<div class=\"card\" style=\"border-radius: 25px;border: 2px solid ;\" >\n" +
                    "        <h5 class=\"card-header\" style=\"color: red\">Unable to Create new Employee</h5>\n" +
                    "        <div class=\"card-body\">");
            out.println(request.getAttribute("errorMessage"));
            out.println("</div>\n" +
                    "    </div>");
            request.removeAttribute("errorMessage");
        }
    %>
    <%
        if (request.getAttribute("successMessage") != null) {
            out.print("<div class=\"card\" style=\"border-radius: 25px;border: 2px solid #73AD21;\" >\n" +
                    "        <h5 class=\"card-header\" style=\"color: blue\">Message</h5>\n" +
                    "        <div class=\"card-body\">");
            out.println(request.getAttribute("successMessage"));
            out.println("</div>\n" +
                    "    </div>");
            request.removeAttribute("successMessage");
        }
    %>
    <div class="card border-dark mb-3" style="border-radius: 25px;border: 2px solid #73AD21;">
        <div class="card-header">
            Search and edit  Employee
        </div>

        <form:form modelAttribute="employee" action="submitEmployeeSearchForm.html" method="post">
            <div class="card-body">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">Employee Id</span>
                    </div>
<%--                    <input name="employeeId" type="text" class="form-control" placeholder="Employee Id"--%>
<%--                           aria-label="employeeId"--%>
<%--                           required>--%>
                    <form:input cssClass="form-control" path="employeeId" placeholder="Employee Id" required="required"></form:input>

                </div>
                <button type="submit" class="btn btn-primary" style="width: 321px">Search employee</button>
            </div>
        </form:form>
    </div>
</div>
</div>
</body>
</html>